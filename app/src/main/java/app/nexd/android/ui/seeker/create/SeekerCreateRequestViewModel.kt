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

class SeekerCreateRequestViewModel : ViewModel() {

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

    fun sendRequest(request: HelpRequestCreateDto) {
        if (request.articles.isNullOrEmpty()) {
            return // request without articles shouldn't be accepted
        }
        with(api) {
            helpRequestsControllerInsertRequestWithArticles(request)
                .subscribe { state.onNext(State.FINISHED) }
        }
    }

    fun state() = LiveDataReactiveStreams.fromPublisher(state)

}
