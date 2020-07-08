package app.nexd.android.ui.seeker.create

import android.content.Context
import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations.map
import androidx.lifecycle.Transformations.switchMap
import androidx.lifecycle.ViewModel
import app.nexd.android.Api
import app.nexd.android.R
import app.nexd.android.api.model.Article
import app.nexd.android.api.model.CreateHelpRequestArticleDto
import app.nexd.android.api.model.HelpRequestCreateDto
import app.nexd.android.api.model.Unit
import app.nexd.android.ui.common.helprequest.HelpRequestCreateArticleBinder.ArticleViewModel
import app.nexd.android.ui.utils.extensions.currentLanguage
import app.nexd.android.ui.utils.extensions.toLiveData
import io.reactivex.BackpressureStrategy.LATEST
import io.reactivex.disposables.CompositeDisposable
import okhttp3.internal.toImmutableList

class SeekerCreateRequestViewModel(private val context: Context, private val api: Api) : ViewModel() {

    sealed class Progress {
        object Idle : Progress()
        object Loading : Progress()
        class Error(@StringRes val message: Int? = null) : Progress()
        object Finished : Progress()
    }

    val progress: MutableLiveData<Progress> = MutableLiveData(Progress.Idle)

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

    class AdditionalInformation : MutableLiveData<String?>(null)

    val additionalInformation = AdditionalInformation()

    private val compositeDisposable = CompositeDisposable()

    val inputs = MutableLiveData(mutableListOf(createArticleViewModel()))

    private val unitsCache = api.articlesControllerGetUnits(context.currentLanguage())
        .replay(1)
        .autoConnect()

    private fun createArticleViewModel(): ArticleViewModel {
        val articleName = MutableLiveData("")

        val article = MediatorLiveData<Article>().apply {
            // change article on articleName change
            val articleForName = switchMap(articleName) { name ->
                val foundArticle = api.articlesControllerFindAll(
                    1,
                    name,
                    null,
                    true,
                    context.currentLanguage(),
                    false)
                    .filter { it.isNotEmpty() }
                    .map { it.first() }
                    .toLiveData(LATEST)

                // only change if name is matching
                map(foundArticle) {
                    if (it.name.compareTo(name, true) == 0) {
                        it
                    } else {
                        null
                    }
                }
            }

            addSource(articleForName, this::setValue)
        }

        val amount = MutableLiveData("1")
        val units = switchMap(article, this::getSortedUnitsForArticle)

        val selectedUnit = MediatorLiveData<Unit>()
            .apply {
                // change unit on article change
                addSource(
                    switchMap(
                        article,
                        this@SeekerCreateRequestViewModel::getMostPopularUnitForArticle
                    ),
                    this::setValue
                )
            }

        val isNew = MutableLiveData(true)

        return ArticleViewModel(
            article,
            articleName,
            amount,
            units,
            selectedUnit,
            isNew,
            listener = object : ArticleViewModel.ActionListener {
                override fun onConfirm(viewModel: ArticleViewModel) {
                    confirmNewArticleInput(viewModel)
                }
            }
        )
    }

    fun confirmNewArticleInput(viewModel: ArticleViewModel) {
        viewModel.isNew.value = false
        inputs.value = inputs.value?.apply { add(createArticleViewModel()) }
    }

    private fun getMostPopularUnitForArticle(article: Article?): LiveData<Unit> {
        return map(getSortedUnitsForArticle(article)) { units ->
            units.firstOrNull()
        }
    }

    private fun getSortedUnitsForArticle(article: Article?): LiveData<List<Unit>> {
        val order = article?.unitIdOrder

        return if (article == null || order.isNullOrEmpty()) {
            unitsCache
        } else {
            unitsCache
                .map { cachedUnitsList ->
                    order
                        .map { id -> cachedUnitsList.first { it.id == id } }
                        .toMutableList()
                        .apply {
                            addAll(cachedUnitsList.filter { !contains(it) })
                        }
                        .toImmutableList()
                }
        }.toLiveData(LATEST)
    }

    internal fun sendRequest() {
        val valid =
            firstName.hasValueOrSetError(firstNameError) &&
                    lastName.hasValueOrSetError(lastNameError) &&
                    street.hasValueOrSetError(streetError) &&
                    number.hasValueOrSetError(numberError) &&
                    zipCode.hasValueOrSetError(zipCodeError) &&
                    city.hasValueOrSetError(cityError) &&
                    phoneNumber.hasValueOrSetError(phoneNumberError) &&
                    !inputs.value?.filter { it.isNew.value ?: false }.isNullOrEmpty()

        if (valid) {
            compositeDisposable.add(
                with(api) {
                    helpRequestsControllerInsertRequestWithArticles(
                        HelpRequestCreateDto()
                            .articles(inputs.value?.map {
                                CreateHelpRequestArticleDto()
                                    .articleId(it.article.value?.id)
                                    .articleName(it.articleName.value)
                                    .articleCount(it.amount.value?.toLong())
                                    .language(CreateHelpRequestArticleDto.LanguageEnum.fromValue(context.currentLanguage().value))
                                    .unitId(it.selectedUnit.value?.id)
                            })
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
            )
        } else {
            progress.value = Progress.Error(R.string.error_message_user_detail_field_missing)
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
