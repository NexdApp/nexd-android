package de.andrestefanov.android.nearbuy.ui.buyer

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import de.andrestefanov.android.nearbuy.api
import de.andrestefanov.android.nearbuy.api.model.Article
import de.andrestefanov.android.nearbuy.api.model.RequestEntity
import de.andrestefanov.android.nearbuy.api.model.ShoppingList
import io.reactivex.BackpressureStrategy
import java.math.BigDecimal

class BuyerRequestDetailViewModel : ViewModel() {

    fun getArticles(): LiveData<List<Article>> {
        return LiveDataReactiveStreams.fromPublisher(
            api.articlesControllerFindAll().toFlowable(BackpressureStrategy.BUFFER)
        )
    }

    fun requestDetails(requestId: BigDecimal): LiveData<RequestEntity> {
        return LiveDataReactiveStreams.fromPublisher(
            api.requestControllerGetSingleRequest(requestId)
                .toFlowable(BackpressureStrategy.BUFFER)
        )
    }

    fun acceptRequest(requestId: BigDecimal) {
        api.shoppingListControllerGetUserLists()
            .map { it.maxBy(ShoppingList::getCreatedAt) }
            .flatMap {
                api.shoppingListControllerAddRequestToList(
                    it.id.toBigDecimal(),
                    requestId
                )
            }
            .subscribe()
    }

}