package app.nexd.android.ui.helper.call

import android.annotation.SuppressLint
import android.app.Application
import android.media.MediaPlayer
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MutableLiveData
import app.nexd.android.api
import app.nexd.android.api.model.Article
import app.nexd.android.api.model.Call
import app.nexd.android.api.model.ConvertedHelpRequestDto
import app.nexd.android.api.model.HelpRequestCreateDto
import io.reactivex.BackpressureStrategy
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import okhttp3.ResponseBody
import java.io.*
import java.util.concurrent.TimeUnit

class CallTranslateViewModel(application: Application) : AndroidViewModel(application) {

    companion object {
        const val TMP_FILENAME = "callTmpFile.wav"
    }

    private val mediaPlayer = MediaPlayer()
    private var updateTimer: Disposable? = null

    val isPlaying = MutableLiveData(false)
    val playbackPosition = MutableLiveData(0)
    val maxPosition = MutableLiveData(0)
    val downloadProgress = MutableLiveData(0f)


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
                .toFlowable(BackpressureStrategy.LATEST)
        )
    }

    fun startPlayback() {
        if (mediaPlayer.isPlaying)
            return
        mediaPlayer.start()
        isPlaying.value = mediaPlayer.isPlaying
        maxPosition.value = mediaPlayer.duration

        updateTimer = AndroidSchedulers.mainThread()
            .schedulePeriodicallyDirect({
                playbackPosition.value = mediaPlayer.currentPosition
            }, 0, 100, TimeUnit.MILLISECONDS)

        mediaPlayer.setOnCompletionListener {
            mediaPlayer.seekTo(0)
            playbackPosition.value = 0
            isPlaying.value = mediaPlayer.isPlaying
            updateTimer?.dispose()
        }
    }

    fun pausePlayback() {
        if (!mediaPlayer.isPlaying)
            return
        mediaPlayer.pause()
        updateTimer?.dispose()
        isPlaying.value = mediaPlayer.isPlaying
    }

    fun togglePlayback() {
        if (mediaPlayer.isPlaying)
            pausePlayback()
        else
            startPlayback()
    }

    fun setPlaybackPosition(position: Int) {
        mediaPlayer.seekTo(position)
    }

    @SuppressLint("CheckResult")
    fun getAudioFile(callId: String): LiveData<Boolean> {
        val response = MutableLiveData<Boolean>()
        api.callsControllerGetCallUrl(callId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                saveFile(it)?.let { filePath ->
                    mediaPlayer.setDataSource(filePath)
                    mediaPlayer.prepare()
                    response.value = true
                }
            }

        return response
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
                File(getApplication<Application>().applicationContext.filesDir, TMP_FILENAME)
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
                while (true) {
                    val read: Int = inputStream.read(fileReader)
                    if (read == -1) {
                        break
                    }
                    outputStream.write(fileReader, 0, read)
                    fileSizeDownloaded += read.toLong()
                    downloadProgress.value = fileSizeDownloaded.toFloat() / fileSize.toFloat()
                }
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