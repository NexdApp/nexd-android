package app.nexd.android.ui.seeker.create

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import app.nexd.android.api
import app.nexd.android.api.model.Article
import app.nexd.android.api.model.CreateRequestArticleDto
import app.nexd.android.api.model.RequestFormDto
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
