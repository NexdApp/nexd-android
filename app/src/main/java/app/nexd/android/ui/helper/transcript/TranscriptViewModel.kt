package app.nexd.android.ui.helper.transcript

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.nexd.android.Api
import app.nexd.android.R
import app.nexd.android.api.model.Call
import app.nexd.android.api.model.CreateHelpRequestArticleDto
import app.nexd.android.api.model.HelpRequestCreateDto
import app.nexd.android.ui.helper.transcript.articles.TranscriptArticlesItemViewModel
import app.nexd.android.ui.utils.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers.mainThread
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy

class TranscriptViewModel(private val api: Api) : ViewModel() {

    /**
     * General error that could happen during the whole transcription flow
     */
    val error = SingleLiveEvent<Int>()

    /**
     * List of currently untranslated calls
     */
    val calls = MutableLiveData<List<Call>>()

    /**
     * Currently selected call to transcript
     */
    val call = MutableLiveData<Call?>(null)

    // Personal information

    val firstName = MutableLiveData<String?>(null)
    val firstNameError = MutableLiveData<Int?>(null)

    val lastName = MutableLiveData<String?>(null)
    val lastNameError = MutableLiveData<Int?>(null)

    val street = MutableLiveData<String?>(null)
    val streetError = MutableLiveData<Int?>(null)

    val number = MutableLiveData<String?>(null)
    val numberError = MutableLiveData<Int?>(null)

    val zipCode = MutableLiveData<String?>(null)
    val zipCodeError = MutableLiveData<Int>(null)

    val city = MutableLiveData<String?>(null)
    val cityError = MutableLiveData<Int?>(null)

    // Articles

    val articles: MutableLiveData<List<TranscriptArticlesItemViewModel>> =
        MutableLiveData(emptyList())

    /**
     * This disposable is to be used for rx subscriptions not bound to a live data lifecycle.
     * It will be cleared on ViewModel onCleared.
     */
    private val compositeDisposable = CompositeDisposable()

    private fun loadArticles() {
        val observable = api.articlesControllerFindAll()
            .map { articles ->
                articles
                    .map { article ->
                        TranscriptArticlesItemViewModel(
                            article.id,
                            MutableLiveData(article.name),
                            MutableLiveData(0L.toString())
                        )
                    }
            }

        val disposable = observable
            .observeOn(mainThread())
            .subscribeBy(
                onNext = { articles.value = it }
            )

        compositeDisposable.add(disposable)
    }

    fun reloadCalls() {
        val calls = api.phoneControllerGetCalls(
            null,
            10,
            false,
            null,
            null,
            null
        )

        val disposable = calls
            .observeOn(mainThread())
            .subscribeBy(
                onNext = {

                    this.calls.setValue(it)
                },
                onError = {
                    Log.e(TranscriptViewModel::class.simpleName, "Failed to load calls", it)
                    this.error.value =
                        R.string.error_message_unknown // TODO: use proper error message
                }
            )

        compositeDisposable.add(disposable)
    }

    fun transcriptCall(call: Call) {
        resetData()

        this.call.value = call

        loadArticles()
    }

    fun cancelTranscription() {
        resetData()
    }

    private fun resetData() {
        call.value = null

        firstName.value = null
        lastName.value = null
        street.value = null
        number.value = null
        zipCode.value = null
        city.value = null
        firstNameError.value = null
        lastNameError.value = null
        streetError.value = null
        numberError.value = null
        zipCodeError.value = null
        cityError.value = null
        error.value = null

        articles.value = emptyList()
    }

    override fun onCleared() {
        super.onCleared()

        compositeDisposable.clear()
    }

    fun validateInfo(): Boolean {
        var valid = true

        if (firstName.value.isNullOrBlank()) {
            firstNameError.value = R.string.error_message_unknown // TODO: use proper error message
            valid = false
        } else {
            firstNameError.value = null
        }

        if (lastName.value.isNullOrBlank()) {
            lastNameError.value = R.string.error_message_unknown // TODO: use proper error message
            valid = false
        } else {
            lastNameError.value = null
        }

        if (street.value.isNullOrBlank()) {
            streetError.value = R.string.error_message_unknown // TODO: use proper error message
            valid = false
        } else {
            streetError.value = null
        }

        if (number.value.isNullOrBlank()) {
            numberError.value = R.string.error_message_unknown // TODO: use proper error message
            valid = false
        } else {
            numberError.value = null
        }

        if (zipCode.value.isNullOrBlank()) {
            zipCodeError.value = R.string.error_message_unknown // TODO: use proper error message
            valid = false
        } else {
            zipCodeError.value = null
        }

        if (city.value.isNullOrBlank()) {
            cityError.value = R.string.error_message_unknown // TODO: use proper error message
            valid = false
        } else {
            cityError.value = null
        }

        return valid
    }

    fun saveHelpRequest() {
        val helpRequestArticles = articles.value?.let { list ->
            list
                .filter { (it.articleCount.value?.toLong() ?: 0L) > 0L }
                .map {
                    CreateHelpRequestArticleDto()
                        .articleId(it.articleId)
                        .articleCount(it.articleCount.value!!.toLong())
                }
        }

        if (helpRequestArticles.isNullOrEmpty()) {
            error.value = R.string.error_message_unknown // TODO: use proper error message
            return
        }

        val data = HelpRequestCreateDto()
            .firstName(firstName.value)
            .lastName(lastName.value)
            .street(street.value)
            .number(number.value)
            .zipCode(zipCode.value)
            .city(city.value)
            .articles(helpRequestArticles)

        val disposable = api.helpRequestsControllerInsertRequestWithArticles(data)
            .observeOn(mainThread())
            .subscribeBy(
                onNext = { resetData() },
                onError = { error.value = R.string.error_message_unknown } // TODO: use proper error message
            )

        compositeDisposable.add(disposable)
    }
}