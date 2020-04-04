package app.nexd.android.ui.helper.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import app.nexd.android.api
import app.nexd.android.api.model.Article
import app.nexd.android.api.model.HelpRequest
import io.reactivex.BackpressureStrategy
import java.math.BigDecimal

class BuyerRequestDetailViewModel : ViewModel() {

    fun getArticles(): LiveData<List<Article>> {
        return LiveDataReactiveStreams.fromPublisher(
            api.articlesControllerFindAll().toFlowable(BackpressureStrategy.BUFFER)
        )
    }

    fun requestDetails(requestId: BigDecimal): LiveData<HelpRequest> {
        return LiveDataReactiveStreams.fromPublisher(
            api.helpRequestsControllerGetSingleRequest(requestId)
                .doOnError {
                    Log.e("Error", it.message.toString())
                }
                .onErrorReturnItem(HelpRequest())
                .toFlowable(BackpressureStrategy.BUFFER)
        )
    }

    fun acceptRequest(requestId: BigDecimal) {
        api.helpListsControllerGetUserLists(userId = null)
            .flatMap { lists ->
                if (lists.isNotEmpty() && lists.any { it.status == ShoppingList.StatusEnum.ACTIVE }) {
                    return@flatMap io.reactivex.Observable.just(
                        lists.filter { list -> list.status == ShoppingList.StatusEnum.ACTIVE }
                            .maxBy(ShoppingList::getCreatedAt))
                } else {
                    return@flatMap api.shoppingListControllerInsertNewShoppingList(
                        ShoppingListFormDto()
                            .requests(0)
                            .status(ShoppingListFormDto.StatusEnum.ACTIVE)
                    )
                }
            }
            .flatMap {
                api.shoppingListControllerAddRequestToList(
                    it.id.toBigDecimal(),
                    requestId
                )
            }
            .doOnError {
                Log.e("Error", it.message.toString())
            }
            .blockingSubscribe()
    }

}