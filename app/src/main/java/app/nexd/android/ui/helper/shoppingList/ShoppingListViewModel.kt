package app.nexd.android.ui.helper.shoppingList

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import app.nexd.android.api
import app.nexd.android.api.model.HelpList
import io.reactivex.BackpressureStrategy
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.Schedulers.io
import io.reactivex.subjects.BehaviorSubject

class ShoppingListViewModel : ViewModel() {

    class ShoppingListEntry(var name: String, var amount: Int) {
        var collected = false
    }
    class ShoppingListEntry(
        var articleAmount: Long,
        var articleName: String,
        var articleId: Long,
        var isCollected: Boolean
    )

    fun getShoppingListArticles(): LiveData<List<ShoppingListEntry>> {

        val observable = api.helpListsControllerGetUserLists("me")
            .map { helpLists ->
                val helpRequests = helpLists
                    .filter { it.status == HelpList.StatusEnum.ACTIVE }
                    .maxBy { it.createdAt }
                    ?.helpRequests ?: emptyList()

                helpRequests
                    .asSequence()
                    .map { it.articles ?: emptyList() }
                    .flatten()
                    .filter { it.article != null }
                    .groupBy { it.article!!.name }
                    .map { entry -> ShoppingListEntry(entry.key, entry.value.sumBy { it.articleCount ?: 0 }) }
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

    fun checkArticle(shoppingListId: Long, articleId: Long) {
        with(api) {
            helpListsControllerModifyArticleInAllHelpRequests(
                shoppingListId.toBigDecimal(),
                articleId,
                true
            ).subscribe {
            }
        }
    }
}