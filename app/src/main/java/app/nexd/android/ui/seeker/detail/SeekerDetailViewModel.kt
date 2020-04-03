package app.nexd.android.ui.seeker.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import app.nexd.android.api
import app.nexd.android.api.model.Article
import app.nexd.android.api.model.RequestEntity
import io.reactivex.BackpressureStrategy

class SeekerDetailViewModel: ViewModel() {

    fun getArticles() : LiveData<List<Article>> {
        return LiveDataReactiveStreams.fromPublisher(
            api.articlesControllerFindAll().toFlowable(BackpressureStrategy.BUFFER)
        )
    }

    fun getRequest(id: Int): LiveData<RequestEntity> {
        return LiveDataReactiveStreams.fromPublisher(
            api.requestControllerGetSingleRequest(id.toBigDecimal())
                .onErrorReturnItem(RequestEntity()) // TODO return state
                .toFlowable(BackpressureStrategy.LATEST))
    }

    fun cancelRequest(requestId: Int) {
        // api.
        // api.request
    }

}