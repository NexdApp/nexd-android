package app.nexd.android.ui.helper.transcript.articles

import android.app.Application
import android.media.MediaPlayer
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MutableLiveData
import app.nexd.android.adapter.MediaPlayerAdapter
import app.nexd.android.adapter.PlaybackInfoListener
import app.nexd.android.api
import app.nexd.android.api.model.Article
import app.nexd.android.api.model.Call
import app.nexd.android.api.model.ConvertedHelpRequestDto
import app.nexd.android.api.model.HelpRequestCreateDto
import app.nexd.android.ui.common.Constants.Companion.DATE_FORMAT
import io.reactivex.BackpressureStrategy
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import okhttp3.ResponseBody
import java.io.*
import java.text.SimpleDateFormat
import java.util.*

class TranscriptArticlesViewModel(application: Application) : AndroidViewModel(application) {

    private val medPlayer = MediaPlayerAdapter()

    private val mediaPlayer = MediaPlayer()
    private var updateTimer: Disposable? = null

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

    fun downloadAudioFile(callId: String) {
        with(api) {
            callsControllerGetCallUrl(callId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    saveFile(it)?.let { filePath ->
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

    private fun saveFile(body: ResponseBody): String? {
        return try {
            val futureStudioIconFile =
                File(getApplication<Application>().applicationContext.cacheDir, "callTmpFile.wav")
            if (!futureStudioIconFile.exists())
                futureStudioIconFile.createNewFile()

            var inputStream: InputStream? = null
            var outputStream: OutputStream? = null
            try {
                val fileReader = ByteArray(4096)
                val fileSize = body.contentLength()
                var fileSizeDownloaded: Long = 0
                inputStream = body.byteStream()
                outputStream = FileOutputStream(futureStudioIconFile)
                downloadStarted.value = true
                isDownloading.value = true
                while (true) {
                    val read: Int = inputStream.read(fileReader)
                    if (read == -1) {
                        break
                    }
                    outputStream.write(fileReader, 0, read)
                    fileSizeDownloaded += read.toLong()
                    downloadProgress.value = (fileSizeDownloaded * 100 / fileSize).toInt()
                }
                isDownloading.value = false
                downloadFinished.value = true
                outputStream.flush()
                futureStudioIconFile.absolutePath
            } catch (e: IOException) {
                e.printStackTrace()
                null
            } finally {
                inputStream?.close()
                outputStream?.close()
            }
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }
}