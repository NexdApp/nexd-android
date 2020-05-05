package app.nexd.android.ui.seeker.create

import androidx.annotation.StringRes
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.nexd.android.Api
import app.nexd.android.R
import app.nexd.android.api.model.CreateHelpRequestArticleDto
import app.nexd.android.api.model.HelpRequestCreateDto
import app.nexd.android.api.model.User
import app.nexd.android.ui.common.HelpRequestCreateArticleBinder
import io.reactivex.android.schedulers.AndroidSchedulers.mainThread
import io.reactivex.disposables.CompositeDisposable

class SeekerCreateRequestViewModel(private val api: Api) : ViewModel() {

    sealed class Progress {
        object Idle : Progress()
        object Loading : Progress()
        class Error(@StringRes val message: Int? = null) : Progress()
        object Finished : Progress()
    }

    val progress = MutableLiveData<Progress>(Progress.Idle)

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

    private val currentUser = MutableLiveData<User?>(null)
    val articles = MutableLiveData(emptyList<HelpRequestCreateArticleBinder.ArticleInput>())
    private var selectedArticles = mutableListOf<CreateHelpRequestArticleDto>()

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
                    !selectedArticles.isNullOrEmpty()

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
                ).subscribe {
                    progress.postValue(Progress.Finished)
                }
            }
        } else {
            progress.value = Progress.Error(R.string.error_message_user_detail_field_missing)
        }
    }

    internal fun confirmSelectedArticles() {
        selectedArticles.clear()
        setSelectedArticles()
        if (selectedArticles.isNotEmpty()) {
            progress.value = Progress.Loading
        } else {
            progress.value = Progress.Error(R.string.seeker_request_create_no_articles)
        }
    }

    internal fun setArticleListSection() {
        if (articles.value.isNullOrEmpty()) {
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
    }

    internal fun setUserInfo() {
        if (currentUser.value == null) {
            val observable = api.userControllerFindMe()
            val disposable = observable.observeOn(mainThread())
                .subscribe {
                    currentUser.value = it
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
    }

    private fun setSelectedArticles() {
        selectedArticles = if (!articles.value.isNullOrEmpty()) {
            articles.value!!.filter { it.amount.value?.toLong() ?: 0L > 0L }.map {
                CreateHelpRequestArticleDto()
                    .articleCount(it.amount.value!!.toLong())
                    .articleId(it.articleId)
            }.toMutableList()
        } else {
            mutableListOf()
        }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

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
