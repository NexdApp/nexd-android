package app.nexd.android.ui.helper.call

import android.app.Application
import androidx.lifecycle.*
import app.nexd.android.R
import app.nexd.android.api
import app.nexd.android.api.model.Article
import io.reactivex.BackpressureStrategy

class CallTranslateViewModel(application: Application) : AndroidViewModel(application) {

    val isPlaying = MutableLiveData(false)
    // val playbackIcon = MutableLiveData(application.getDrawable(R.drawable.ic_play_arrow_black_24dp))


    fun getArticles() : LiveData<List<Article>> {
        return LiveDataReactiveStreams.fromPublisher(
            api.articlesControllerFindAll().toFlowable(BackpressureStrategy.BUFFER)
        )
    }

    fun togglePlayback() {
        isPlaying.value = !(isPlaying.value ?: true)
    }
}