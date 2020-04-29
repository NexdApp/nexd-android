package app.nexd.android.ui.seeker.create

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import app.nexd.android.Api
import app.nexd.android.api.model.Article
import app.nexd.android.api.model.HelpRequestCreateDto
import app.nexd.android.api.model.User
import io.reactivex.BackpressureStrategy
import io.reactivex.processors.BehaviorProcessor

class SeekerCreateRequestViewModel(private val api: Api) : ViewModel() {

    enum class State {
        LOADING,
        IDLE,
        FINISHED
    }

    private val state = BehaviorProcessor.createDefault(State.LOADING)
    lateinit var requestToConfirm: HelpRequestCreateDto

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

    fun sendRequest() {
        if (requestToConfirm.articles.isNullOrEmpty()) {
            return // request without articles shouldn't be accepted
        }
        with(api) {
            helpRequestsControllerInsertRequestWithArticles(requestToConfirm)
                .subscribe { state.onNext(State.FINISHED) }
        }
    }

    fun state() = LiveDataReactiveStreams.fromPublisher(state)

}
