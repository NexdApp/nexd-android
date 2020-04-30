package app.nexd.android.ui.helper.transcript

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.nexd.android.Api
import app.nexd.android.R
import app.nexd.android.api.model.Call
import app.nexd.android.api.model.CreateHelpRequestArticleDto
import app.nexd.android.api.model.HelpRequestCreateDto
import app.nexd.android.ui.common.HelpRequestCreateArticleBinder
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
     * Currently selected call to transcript
     */
    val call = MutableLiveData<Call?>(null)
    val callError = MutableLiveData<Int?>(null)

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
    val articles = MutableLiveData(emptyList<HelpRequestCreateArticleBinder.ArticleInput>())

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
                        HelpRequestCreateArticleBinder.ArticleInput(
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

    fun transcriptCall() {
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

        compositeDisposable.add(api.phoneControllerGetCalls(
            null,
            1,
            false,
            null,
            null,
            null
        )
            .observeOn(mainThread())
            .subscribe({
                val call = it.firstOrNull()
                if (call == null) {
                    callError.value = R.string.transcript_error_empty_calls
                }
                this.call.value = call
            }, {
                error.value = R.string.error_message_unknown // TODO: use proper error message
            }))

        loadArticles()
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
                .filter { (it.amount.value?.toLong() ?: 0L) > 0L }
                .map {
                    CreateHelpRequestArticleDto()
                        .articleId(it.articleId)
                        .articleCount(it.amount.value!!.toLong())
                }
        }

        if (helpRequestArticles.isNullOrEmpty()) {
            error.value = R.string.error_message_unknown // TODO: use proper error message
            return
        }

        val data = HelpRequestCreateDto().also { dto ->
            dto.firstName = firstName.value
            dto.lastName = lastName.value
            dto.street = street.value
            dto.number = number.value
            dto.zipCode = zipCode.value
            dto.city = city.value
            dto.phoneNumber = call.value?.phoneNumber
            dto.articles = helpRequestArticles
        }

        val disposable = api.phoneControllerConverted(call.value?.sid, data)
            .observeOn(mainThread())
            .subscribeBy(
                onNext = { call.value = null },
                onError = { error.value = R.string.error_message_unknown } // TODO: use proper error message
            )

        compositeDisposable.add(disposable)
    }
}