package app.nexd.android.ui.helper.overview

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import app.nexd.android.Preferences
import app.nexd.android.api
import app.nexd.android.api.model.RequestEntity
import app.nexd.android.api.model.ShoppingList
import io.reactivex.BackpressureStrategy
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

class BuyerOverviewViewModel(application: Application) : AndroidViewModel(application) {

    private val reload = BehaviorSubject.create<Unit>()

    fun getMyAcceptedRequests(): LiveData<List<RequestEntity>> {
        val observable = reload.flatMap {
            api.shoppingListControllerGetUserLists()
                .map { lists ->
                    if (lists.any { it.status == ShoppingList.StatusEnum.ACTIVE })
                        lists.maxBy { it.status == ShoppingList.StatusEnum.ACTIVE }
                    else
                        ShoppingList()
                }
                .flatMapSingle { list ->
                    Observable.fromIterable(list.requests).flatMap { request ->
                        api.requestControllerGetSingleRequest(request.requestId.toBigDecimal())
                    }.toList()
                }
                .doOnError {
                    Log.e("Error", it.message.toString())
                }
                .onErrorReturnItem(emptyList())
        }
        return LiveDataReactiveStreams.fromPublisher(observable.toFlowable(BackpressureStrategy.BUFFER))
    }

    fun getOtherOpenRequests(): LiveData<List<RequestEntity>> {
        val observable = reload.flatMap {
            api.requestControllerGetAll(
                null,
                null
            ).map { requests ->
                requests.filter { request ->
                    request.requesterId != Preferences.getUserId(getApplication())
                        && request.status == RequestEntity.StatusEnum.PENDING
                }
            }.doOnError { t ->
                Log.e("Error", t.message.toString())
            }
                .onErrorReturnItem(emptyList())
        }
        return LiveDataReactiveStreams.fromPublisher(observable.toFlowable(BackpressureStrategy.BUFFER))
    }

    fun reloadData() {
        reload.onNext(Unit)
    }

}
