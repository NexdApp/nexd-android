package app.nexd.android.ui.helper.shoppingList

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import app.nexd.android.api
import app.nexd.android.api.model.HelpList
import io.reactivex.BackpressureStrategy

class ShoppingListViewModel : ViewModel() {

    class ShoppingListEntry(
        var articleName: String,
        var articleAmount: Int,
        var articleId: Int,
        var isCollected: Boolean
    )

    fun getShoppingListArticles(): LiveData<List<ShoppingListEntry>> {

        val observable = api.helpListsControllerGetUserLists(null) // TODO: maybe this should be "me"
            .map { helpLists ->
                val helpRequests = helpLists
                    .filter { it.status == HelpList.StatusEnum.ACTIVE }
                    .maxBy { it.createdAt }
                    ?.helpRequests ?: emptyList()

                helpRequests
                    .asSequence() // performance tweak
                    .map { it.articles ?: emptyList() } // if article list == null, take empty list
                    .flatten() // flatten nested articles to one list
                    .filter { it.article != null } // if article is unknown (null), don't display it
                    .groupBy { it.article!! } // group to a Map<Article, List<HelpRequestArticle>
                    .map { entry -> // map to ShoppingListEntry
                        ShoppingListEntry(
                            articleName = entry.key.name,
                            articleAmount = entry.value.sumBy { it.articleCount ?: 0 },
                            articleId = entry.key.id,
                            /* TODO: we try to display "done" state of multiple articles in one checkbox */
                            isCollected = entry.value.all { it.articleDone ?: false }
                        )
                    }
                    .toList()
            }
            .onErrorReturnItem(emptyList())

        return LiveDataReactiveStreams.fromPublisher(observable.toFlowable(BackpressureStrategy.BUFFER))
    }

    fun getShoppingList(): LiveData<HelpList> {
        return LiveDataReactiveStreams.fromPublisher(
            api.helpListsControllerGetUserLists(null)
                .map { lists -> lists.first { it.status == HelpList.StatusEnum.ACTIVE } }
                .toFlowable(BackpressureStrategy.LATEST)
        )
    }

    fun checkArticle(shoppingListId: Int, articleId: Int) {
        with(api) {
            helpListsControllerModifyArticleInAllHelpRequests(
                shoppingListId,
                articleId,
                true
            ).subscribe {
            }
        }
    }
}