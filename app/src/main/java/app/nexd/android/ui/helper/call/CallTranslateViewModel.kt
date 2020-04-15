package app.nexd.android.ui.helper.call

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MutableLiveData
import app.nexd.android.adapter.MediaPlayerAdapter
import app.nexd.android.adapter.PlaybackInfoListener
import app.nexd.android.adapter.SaveFileAdapter
import app.nexd.android.api
import app.nexd.android.api.model.Article
import app.nexd.android.api.model.Call
import app.nexd.android.api.model.ConvertedHelpRequestDto
import app.nexd.android.api.model.HelpRequestCreateDto
import app.nexd.android.ui.common.Constants.Companion.DATE_FORMAT
import io.reactivex.BackpressureStrategy
import io.reactivex.android.schedulers.AndroidSchedulers
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class CallTranslateViewModel(application: Application) : AndroidViewModel(application) {

    private val medPlayer = MediaPlayerAdapter()
    private val saveFile = SaveFileAdapter()

    val timestamp = MutableLiveData("")

    val isPlaying = MutableLiveData(false)
    val playbackPosition = MutableLiveData(0)
    val maxPosition = MutableLiveData(0)

    val downloadProgress = MutableLiveData(0)
    val downloadStarted = MutableLiveData(false)
    val isDownloading = MutableLiveData(false)
    val downloadFinished = MutableLiveData(false)

    init {
        medPlayer.setPlaybackListener(object : PlaybackInfoListener() {
            override fun onDurationChanged(duration: Int) {
                maxPosition.value = duration
            }

            override fun onPositionChanged(position: Int) {
                playbackPosition.value = position
            }

            override fun onPlayerStateChanged(state: PlayerState) {
                isPlaying.value = when (state) {
                    PlayerState.PAUSED -> false
                    PlayerState.PLAYING -> true
                    else -> false
                }
            }
        })
        saveFile.setListener(object : SaveFileAdapter.SaveFileListener() {
            override fun onDownloadStarted() {
                downloadStarted.value = true
                isDownloading.value = true
            }

            override fun onDownloadProgress(progress: Long) {
                downloadProgress.value = progress.toInt()
            }

            override fun onDownloadFinished() {
                downloadFinished.value = true
                isDownloading.value = false
            }
        })
    }

    fun startPlayback() {
        medPlayer.play()
    }

    fun pausePlayback() {
        medPlayer.pause()
    }

    fun togglePlayback() {
        if (medPlayer.isPlaying())
            pausePlayback()
        else
            startPlayback()
    }

    fun setPlaybackPosition(position: Int) {
        medPlayer.seekTo(position)
    }

    fun getCall(callId: String): LiveData<Call> {
        return LiveDataReactiveStreams.fromPublisher(
            api.callsControllerCalls(
                null,
                null,
                null,
                null,
                null
            )
                .map { list -> list.first { it.sid == callId } }
                .observeOn(AndroidSchedulers.mainThread())
                .map { // TODO change to subscribe if binding complete
                    timestamp.value =
                        SimpleDateFormat(DATE_FORMAT, Locale.getDefault()).format(it.created)
                    it
                }
                .toFlowable(BackpressureStrategy.LATEST)
        )
    }

    fun downloadAudioFile(callId: String) {
        with(api) {
            callsControllerGetCallUrl(callId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    saveFile.saveIntoFile(
                        it,
                        File(
                            getApplication<Application>().applicationContext.cacheDir,
                            "callTmpFile.wav"
                        )
                    )?.let { filePath ->
                        medPlayer.loadMedia(filePath)
                    }
                }
        }
    }

    fun getArticles(): LiveData<List<Article>> {
        return LiveDataReactiveStreams.fromPublisher(
            api.articlesControllerFindAll().toFlowable(BackpressureStrategy.BUFFER)
        )
    }

    fun convertToHelpRequest(callSid: String, requestDto: HelpRequestCreateDto): LiveData<Call> {
        return LiveDataReactiveStreams.fromPublisher(
            api.helpRequestsControllerInsertRequestWithArticles(requestDto)
                .flatMap {
                    api.callsControllerConverted(
                        callSid,
                        ConvertedHelpRequestDto()
                            .helpRequestId(it.id)
                    )
                }
                .toFlowable(BackpressureStrategy.LATEST)
        )
    }
}