package app.nexd.android.ui.helper.detail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.nexd.android.Api
import app.nexd.android.api.model.HelpList
import app.nexd.android.api.model.HelpListCreateDto
import app.nexd.android.api.model.HelpRequestArticle
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers.io

class HelperDetailViewModel(private val api: Api) : ViewModel() {


    val additionalRequest = MutableLiveData<String>()
    val firstName = MutableLiveData<String>()
    val lastName = MutableLiveData<String>()
    val street = MutableLiveData<String>()
    val number = MutableLiveData<String>()
    val zipCode = MutableLiveData<String>()
    val city = MutableLiveData<String>()
    val requestArticles = MutableLiveData<List<HelpRequestArticle>>()
    val requestIsAccepted = MutableLiveData<Boolean>()
    val buttonText = MutableLiveData<String>()
    val idOfRequest = MutableLiveData<Long>()

    private val compositeDisposable = CompositeDisposable()

    fun acceptRequest(requestId: Long) {
        api.helpListsControllerGetUserLists(null)
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
            .doOnError {
                Log.e("Error", it.message.toString())
            }
            .blockingSubscribe()
    }

    fun setInfo(requestId: Long) {
        val observable = api.helpRequestsControllerGetSingleRequest(requestId)

        val disposable = observable
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onNext = {
                    requestArticles.value = it.articles
                    firstName.value = it.firstName
                    lastName.value = it.lastName
                    street.value = it.street
                    number.value = it.number
                    zipCode.value = it.zipCode
                    city.value = it.city
                    additionalRequest.value = it.additionalRequest
                    idOfRequest.value = it.id
                    requestIsAccepted.value = (it.helpListId != null)
                }
            )

        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}