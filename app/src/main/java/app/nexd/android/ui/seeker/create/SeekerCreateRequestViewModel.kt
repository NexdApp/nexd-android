package app.nexd.android.ui.seeker.create

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.nexd.android.Api
import app.nexd.android.R
import app.nexd.android.api.model.Article
import app.nexd.android.api.model.CreateHelpRequestArticleDto
import app.nexd.android.api.model.HelpRequestCreateDto
import app.nexd.android.api.model.User
import app.nexd.android.ui.common.HelpRequestCreateArticleBinder
import io.reactivex.BackpressureStrategy
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

class SeekerCreateRequestViewModel(private val api: Api) : ViewModel() {

    sealed class Progress {
        object Idle : Progress()
        class Error(@StringRes val message: Int) : Progress()
        object Finished : Progress()
    }

    val progress = MutableLiveData<Progress>(Progress.Idle)

    val articles = LiveDataReactiveStreams.fromPublisher(
        api.articlesControllerFindAll()
            .map { articles ->
                articles.map { HelpRequestCreateArticleBinder.ArticleInput(it) }
            }
            .onErrorReturn {
                progress.value = Progress.Error(R.string.error_message_unknown)
                emptyList()
            }
            .toFlowable(BackpressureStrategy.BUFFER)
    )

    private val compositeDisposable = CompositeDisposable()

    fun sendRequest() {
        val articles = this.articles.value ?: return

        // TODO add additional information when in design
        val disposable = api.userControllerFindMe()
            .flatMap { currentUser ->
                api.helpRequestsControllerInsertRequestWithArticles(
                    HelpRequestCreateDto()
                        .articles(
                            articles.filter { it.amount.value?.toLong() ?: 0L > 0L }
                                .map {
                                    CreateHelpRequestArticleDto()
                                        .articleCount(it.amount.value!!.toLong())
                                        .articleId(it.articleId)
                                }
                        )
                        .street(currentUser.street)
                        .number(currentUser.number)
                        .zipCode(currentUser.zipCode)
                        .city(currentUser.city)
                        .phoneNumber(currentUser.phoneNumber)
                )
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                progress.value = Progress.Finished
            }, {
                progress.value =
                    Progress.Error(R.string.error_message_unknown) // TODO add proper error handling
            })

        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
