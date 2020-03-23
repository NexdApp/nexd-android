package de.andrestefanov.android.nearbuy.ui.seeker.create

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import de.andrestefanov.android.nearbuy.api
import de.andrestefanov.android.nearbuy.api.model.Article
import de.andrestefanov.android.nearbuy.api.model.CreateRequestArticleDto
import de.andrestefanov.android.nearbuy.api.model.RequestFormDto
import io.reactivex.BackpressureStrategy

class CreateHelpRequestViewModel : ViewModel() {

    fun getArticles() : LiveData<List<Article>> {
        return LiveDataReactiveStreams.fromPublisher(
            api.articlesControllerFindAll().toFlowable(BackpressureStrategy.BUFFER)
        )
    }

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
    }

    fun sendRequest(request: RequestFormDto) {
        api.requestControllerInsertRequestWithArticles(request).subscribe()
    }

}
