package app.nexd.android.ui.helper.detail

import androidx.annotation.StringRes
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.nexd.android.Api
import app.nexd.android.R
import app.nexd.android.api.model.HelpList
import app.nexd.android.api.model.HelpListCreateDto
import app.nexd.android.api.model.HelpRequestArticle
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy

class HelperDetailViewModel(private val api: Api) : ViewModel() {

    sealed class Progress {
        object Idle : Progress()
        object Loading : Progress()
        class Finished(@StringRes val message: Int) : Progress()
        class Error(val message: String) : Progress()
    }

    val additionalRequest = MutableLiveData<String>()
    val firstName = MutableLiveData<String>()
    val lastName = MutableLiveData<String>()
    val street = MutableLiveData<String>()
    val number = MutableLiveData<String>()
    val zipCode = MutableLiveData<String>()
    val city = MutableLiveData<String>()
    val requestArticles = MutableLiveData<List<HelpRequestArticle>>()
    val requestIsAccepted = MutableLiveData<Boolean>()
    val idOfRequest = MutableLiveData<Long>()

    val progress = MutableLiveData<Progress>(Progress.Idle)

    private val compositeDisposable = CompositeDisposable()

    fun acceptOrDeclineRequest(requestId: Long) {
        progress.value = Progress.Loading
        requestIsAccepted.value?.let {
            if (it) declineRequest(requestId)
            else acceptRequest(requestId)
        }
    }

    private fun acceptRequest(requestId: Long) {
        val disposable = api.helpListsControllerGetUserLists(null)
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
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onError = {
                    progress.value = Progress.Error(it.message ?: "Unknown Error")
                },
                onComplete = {
                    progress.value =
                        Progress.Finished(R.string.helper_request_detail_request_accepted)
                }
            )
        compositeDisposable.add(disposable)

    }

    private fun declineRequest(requestId: Long) {
        val helpListToDecline =
            api.helpRequestsControllerGetSingleRequest(requestId)
        val disposable = helpListToDecline
            .observeOn(AndroidSchedulers.mainThread())
            .flatMap { helpListId ->
                api.helpListsControllerDeleteHelpRequestFromHelpList(
                    helpListId.helpListId,
                    requestId
                )

            }.observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onError = {
                    progress.value = Progress.Error(it.message ?: "Unknown Error")
                },
                onComplete = {
                    progress.value =
                        Progress.Finished(R.string.helper_request_detail_request_declined)
                }
            )
        compositeDisposable.add(disposable)
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