package app.nexd.android.ui.helper.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import app.nexd.android.api
import app.nexd.android.api.model.Article
import app.nexd.android.api.model.HelpList
import app.nexd.android.api.model.HelpListCreateDto
import app.nexd.android.api.model.HelpRequest
import io.reactivex.BackpressureStrategy
import java.math.BigDecimal

class BuyerRequestDetailViewModel : ViewModel() {

    fun getArticles(): LiveData<List<Article>> {
        return LiveDataReactiveStreams.fromPublisher(
            api.articlesControllerFindAll()
                .map { it.toList() }
                .toFlowable(BackpressureStrategy.BUFFER)
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

    fun acceptRequest(requestId: Long) {
        api.helpListsControllerGetUserLists(userId = null)
            .flatMap { lists ->
                if (lists.isNotEmpty() && lists.any { it.status == HelpList.StatusEnum.ACTIVE }) {
                    return@flatMap io.reactivex.Observable.just(
                        lists.filter { list -> list.status == HelpList.StatusEnum.ACTIVE }
                            .maxBy(HelpList::getCreatedAt))
                } else {
                    return@flatMap api.helpListsControllerInsertNewHelpList(
                        HelpListCreateDto()
                    )
                }
            }
            .flatMap {
                api.helpListsControllerAddHelpRequestToList(
                    it.id.toBigDecimal(),
                    requestId.toBigDecimal()
                )
            }
            .doOnError {
                Log.e("Error", it.message.toString())
            }
            .blockingSubscribe()
    }

}