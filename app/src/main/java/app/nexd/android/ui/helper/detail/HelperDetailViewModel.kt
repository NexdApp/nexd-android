package app.nexd.android.ui.helper.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.nexd.android.api
import app.nexd.android.api.model.HelpList
import app.nexd.android.api.model.HelpListCreateDto
import app.nexd.android.api.model.HelpRequest
import io.reactivex.BackpressureStrategy
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers.io

class HelperDetailViewModel : ViewModel() {

    sealed class Progress {
        object Idle: Progress()
        class Error(val message: String): Progress()
        object Finished: Progress()
    }

    val progress = MutableLiveData<Progress>(Progress.Idle)

    fun requestDetails(requestId: Long): LiveData<HelpRequest> {
        return LiveDataReactiveStreams.fromPublisher(
            api.helpRequestsControllerGetSingleRequest(requestId)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError {
                    progress.value = Progress.Error(it.message.toString())
                    HelpRequest()
                }
                .toFlowable(BackpressureStrategy.BUFFER)
        )
    }

    fun acceptRequest(requestId: Long) {
        with(api) {
            helpListsControllerGetUserLists(null)
                .map { lists ->
                    if (lists.any { it.status == HelpList.StatusEnum.ACTIVE })
                        lists.first { it.status == HelpList.StatusEnum.ACTIVE }
                    else
                        HelpList()
                }
                .flatMap { shoppingList ->
                    if (shoppingList.id == null) { // no help list found
                        api.helpListsControllerInsertNewHelpList(
                            HelpListCreateDto()
                                .addHelpRequestsIdsItem(requestId)
                        )
                    } else {
                        api.helpListsControllerAddHelpRequestToList(
                            shoppingList.id,
                            requestId
                        )
                    }
                }
                .subscribeOn(io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    progress.value = Progress.Finished
                }, {
                    progress.value = Progress.Error(it.message.toString())
                })
        }
    }

}