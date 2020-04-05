package app.nexd.android.ui.helper.delivery

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.nexd.android.api
import app.nexd.android.api.model.HelpList
import app.nexd.android.api.model.HelpListCreateDto
import io.reactivex.BackpressureStrategy
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers

class DeliveryViewModel : ViewModel() {

    fun getShoppingList(): LiveData<HelpList> {
        return LiveDataReactiveStreams.fromPublisher(
            api.helpListsControllerGetUserLists(null)
                .map { lists -> lists.first { it.status == HelpList.StatusEnum.ACTIVE } }
                .doOnError {
                    Log.e("Error", it.message.toString())
                }
                .onErrorReturnItem(HelpList())
                .toFlowable(BackpressureStrategy.BUFFER))
    }

    fun completeShoppingList(shoppingListId: Long): LiveData<Boolean> {
        val response = MutableLiveData<Boolean>()
        with(api) {
            helpListsControllerUpdateHelpLists(
                shoppingListId.toInt(), // TODO remove if backend
                HelpListCreateDto()
                    .status(HelpListCreateDto.StatusEnum.COMPLETED)
            )
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    response.value = true
                }
        }
        return response
    }

}