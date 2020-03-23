package de.andrestefanov.android.nearbuy.ui.seeker.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import de.andrestefanov.android.nearbuy.api
import de.andrestefanov.android.nearbuy.api.model.Article
import de.andrestefanov.android.nearbuy.api.model.RequestEntity
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
