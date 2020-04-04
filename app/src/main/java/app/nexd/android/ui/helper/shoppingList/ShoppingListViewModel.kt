package app.nexd.android.ui.helper.shoppingList

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import app.nexd.android.api
import app.nexd.android.api.model.HelpList
import io.reactivex.BackpressureStrategy
import io.reactivex.schedulers.Schedulers

class ShoppingListViewModel : ViewModel() {

    class ShoppingListEntry(var name: String, var amount: Long) {
        var collected = false
    }

    fun getShoppingList(): LiveData<HelpList> {
        return LiveDataReactiveStreams.fromPublisher(
            api.helpListsControllerGetUserLists(null)
                .map { lists -> lists.filter { it.status == HelpList.StatusEnum.ACTIVE } }
                .map { it.first() }
                .doOnError {
                    Log.e("Error", it.message.toString())
                }
                .onErrorReturnItem(HelpList())
                .toFlowable(BackpressureStrategy.BUFFER))
    }

    fun getShoppingListArticles(): LiveData<List<ShoppingListEntry>> {
        val observable = api.helpListsControllerGetUserLists(null)
            .map { lists -> lists.first { it.status == HelpList.StatusEnum.ACTIVE } }
            .flatMapIterable { shoppingList ->
                val shoppingListArticles = mutableMapOf<Long, ShoppingListEntry>()
                shoppingList.helpRequests.forEach { request ->
                    // request.articles.filter { it.articleCount > BigDecimal.ZERO }
                    request.articles!!.forEach { requestArticle ->
                        if (shoppingListArticles.containsKey(requestArticle.article.id)) {
                            shoppingListArticles[requestArticle.article.id]!!.amount +=
                                requestArticle.articleCount
                        } else {
                            shoppingListArticles[requestArticle.article.id] = ShoppingListEntry(
                                requestArticle.article.name,
                                requestArticle.articleCount
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
            .subscribeOn(Schedulers.io())

        return LiveDataReactiveStreams.fromPublisher(observable.toFlowable(BackpressureStrategy.BUFFER))
    }


}