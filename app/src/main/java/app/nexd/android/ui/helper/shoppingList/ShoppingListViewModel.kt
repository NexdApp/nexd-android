package app.nexd.android.ui.helper.shoppingList

import android.util.Log
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

    class ShoppingListEntry(
        var articleAmount: Long,
        var articleName: String,
        var articleId: Long,
        var isCollected: Boolean
    )

    fun getShoppingListArticles(): LiveData<List<ShoppingListEntry>> {
        val observable =
                api.helpListsControllerGetUserLists(null)
                    .map { lists -> lists.first { it.status == HelpList.StatusEnum.ACTIVE } }
                    .flatMapIterable { shoppingList ->
                        val shoppingListArticles = mutableMapOf<Long, ShoppingListEntry>()
                        shoppingList.helpRequests.forEach { request ->
                            request.articles?.forEach { requestArticle ->
                                if (shoppingListArticles.containsKey(requestArticle.article.id)) {
                                    shoppingListArticles[requestArticle.article.id]!!.articleAmount +=
                                        requestArticle.articleCount
                                } else {
                                    shoppingListArticles[requestArticle.article.id] =
                                        ShoppingListEntry(
                                            requestArticle.articleCount,
                                            requestArticle.article.name,
                                            requestArticle.article.id,
                                            requestArticle.articleDone
                                        )
                                }
                            }
                        }
                        shoppingListArticles.values
                    }
                .doOnError {
                    Log.e("Error", it.message.toString())
                }
                .toList()
                .toObservable()
                .onErrorReturnItem(emptyList())
                .subscribeOn(io())

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