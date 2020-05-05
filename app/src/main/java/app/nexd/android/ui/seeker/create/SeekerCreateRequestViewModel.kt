package app.nexd.android.ui.seeker.create

import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.nexd.android.Api
import app.nexd.android.R
import app.nexd.android.api.model.CreateHelpRequestArticleDto
import app.nexd.android.api.model.HelpRequestCreateDto
import app.nexd.android.ui.common.HelpRequestCreateArticleBinder
import io.reactivex.android.schedulers.AndroidSchedulers.mainThread
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.processors.BehaviorProcessor

class SeekerCreateRequestViewModel(private val api: Api) : ViewModel() {

    enum class State {
        LOADING,
        IDLE,
        PROCESSING,
        FINISHED
    }

    private val state = BehaviorProcessor.createDefault(State.LOADING)

    val firstName = MutableLiveData<String?>()
    val firstNameError = MutableLiveData<Int?>(null)

    val lastName = MutableLiveData<String?>()
    val lastNameError = MutableLiveData<Int?>(null)

    val street = MutableLiveData<String?>()
    val streetError = MutableLiveData<Int?>(null)

    val number = MutableLiveData<String?>()
    val numberError = MutableLiveData<Int?>(null)

    val zipCode = MutableLiveData<String?>()
    val zipCodeError = MutableLiveData<Int?>(null)

    val city = MutableLiveData<String?>()
    val cityError = MutableLiveData<Int?>(null)

    val phoneNumber = MutableLiveData<String?>()
    val phoneNumberError = MutableLiveData<Int?>(null)

    val additionalInformation = MutableLiveData<String>()

    val articles = MutableLiveData(emptyList<HelpRequestCreateArticleBinder.ArticleInput>())

    private var selectedArticles = emptyList<CreateHelpRequestArticleDto>()


    private val compositeDisposable = CompositeDisposable()

    internal fun sendRequest() {
        val success =
            firstName.hasValueOrSetError(firstNameError) &&
                    lastName.hasValueOrSetError(lastNameError) &&
                    street.hasValueOrSetError(streetError) &&
                    number.hasValueOrSetError(numberError) &&
                    zipCode.hasValueOrSetError(zipCodeError) &&
                    city.hasValueOrSetError(cityError) &&
                    phoneNumber.hasValueOrSetError(phoneNumberError) &&
                    !articles.value.isNullOrEmpty()

        if (success) {

            with(api) {
                helpRequestsControllerInsertRequestWithArticles(
                    HelpRequestCreateDto()
                        .articles(selectedArticles)
                        .firstName(firstName.value)
                        .lastName(lastName.value)
                        .street(street.value)
                        .number(number.value)
                        .zipCode(zipCode.value)
                        .city(city.value)
                        .phoneNumber(phoneNumber.value)
                        .additionalRequest(additionalInformation.value)
                ).subscribe { state.onNext(State.FINISHED) }
            }
        }
    }

    internal fun selectedArticlesListIsNotEmpty(): Boolean {
        setSelectedArticles()
        return if (selectedArticles.isNotEmpty()) {
            state.onNext(State.LOADING)
            true
        } else {
            false
        }
    }

    internal fun getArticleListSection() {
        val observable = api.articlesControllerFindAll().map {
            it.map { article ->
                HelpRequestCreateArticleBinder.ArticleInput(
                    article.id,
                    MutableLiveData(article.name),
                    MutableLiveData(0L.toString())
                )
            }
        }
        val disposable = observable
            .observeOn(mainThread())
            .subscribe {
                articles.value = it
            }
        compositeDisposable.add(disposable)
    }

    internal fun setUserInfo() {
        val observable = api.userControllerFindMe()
        val disposable = observable.observeOn(mainThread())
            .subscribe {
                firstName.value = it.firstName ?: ""
                lastName.value = it.lastName ?: ""
                street.value = it.street ?: ""
                number.value = it.number ?: ""
                zipCode.value = it.zipCode ?: ""
                city.value = it.city ?: ""
                phoneNumber.value = it.phoneNumber ?: ""
            }
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    private fun setSelectedArticles() {
        selectedArticles = if (!articles.value.isNullOrEmpty()) {
            articles.value!!.filter { it.amount.value?.toLong() ?: 0L > 0L }.map {
                CreateHelpRequestArticleDto()
                    .articleCount(it.amount.value!!.toLong())
                    .articleId(it.articleId)
            }
        } else {
            emptyList()
        }
    }

    fun state() = LiveDataReactiveStreams.fromPublisher(state)

    private fun MutableLiveData<String?>.hasValueOrSetError(errorField: MutableLiveData<Int?>): Boolean {
        return if (this.value.isNullOrBlank()) {
            errorField.value = R.string.error_message_user_detail_field_missing
            false
        } else {
            errorField.value = null
            true
        }
    }



}
