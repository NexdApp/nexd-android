package app.nexd.android.ui.seeker.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import app.nexd.android.api
import app.nexd.android.api.model.Article
import app.nexd.android.api.model.RequestEntity
import io.reactivex.BackpressureStrategy

class SeekerOverviewViewModel : ViewModel() {

    fun getHelpRequests(): LiveData<List<RequestEntity>> {
        return LiveDataReactiveStreams.fromPublisher(
            api.requestControllerGetAll(null, null).toFlowable(BackpressureStrategy.BUFFER)
        )
    }

    fun getArticles() : LiveData<List<Article>> {
        return LiveDataReactiveStreams.fromPublisher(
            api.articlesControllerFindAll().toFlowable(BackpressureStrategy.BUFFER)
        )
    }

}
