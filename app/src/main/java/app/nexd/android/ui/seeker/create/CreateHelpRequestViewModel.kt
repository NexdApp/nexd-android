package app.nexd.android.ui.seeker.create

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import app.nexd.android.api
import app.nexd.android.api.model.Article
import app.nexd.android.api.model.HelpRequestCreateDto
import app.nexd.android.api.model.User
import io.reactivex.BackpressureStrategy
import io.reactivex.functions.Consumer
import io.reactivex.processors.BehaviorProcessor
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.Subject
import org.reactivestreams.Processor

class CreateHelpRequestViewModel : ViewModel() {

    enum class State {
        LOADING,
        IDLE,
        FINISHED
    }

    private val state = BehaviorProcessor.createDefault(State.LOADING)

    fun getCurrentUser(): LiveData<User> {
        return LiveDataReactiveStreams.fromPublisher(
            api.userControllerFindMe().toFlowable(BackpressureStrategy.LATEST)
        )
    }

    fun getArticles() : LiveData<List<Article>> {
        return LiveDataReactiveStreams.fromPublisher(
            api.articlesControllerFindAll().toFlowable(BackpressureStrategy.BUFFER)
        )
    }

    /*
    fun getRequestWithArticles(): LiveData<RequestFormDto> {
        val data = api.articlesControllerFindAll()
            .map { list ->
                RequestFormDto()
                    .articles(list.map {
                        CreateRequestArticleDto()
                            .articleId(it.id)
                            .articleCount(0)
                    })
                    .city("")
                    .additionalRequest("")
                    .deliveryComment("")
                    .zipCode("") // TODO insert zip
                    .phoneNumber("")
                    .street("")
            }
        return LiveDataReactiveStreams.fromPublisher(data.toFlowable(BackpressureStrategy.BUFFER))
    }*/

    fun sendRequest(request: HelpRequestCreateDto) {
        with(api) {
            helpRequestsControllerInsertRequestWithArticles(request)
                .subscribe { state.onNext(State.FINISHED) }
        }
    }

    fun state() = LiveDataReactiveStreams.fromPublisher(state)

}
