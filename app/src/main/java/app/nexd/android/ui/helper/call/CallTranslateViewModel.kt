package app.nexd.android.ui.helper.call

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MutableLiveData
import app.nexd.android.api
import app.nexd.android.api.model.Article
import app.nexd.android.api.model.Call
import io.reactivex.BackpressureStrategy
import okhttp3.ResponseBody
import java.io.*

class CallTranslateViewModel(application: Application) : AndroidViewModel(application) {

    val isPlaying = MutableLiveData(false)
    // val playbackIcon = MutableLiveData(application.getDrawable(R.drawable.ic_play_arrow_black_24dp))


    fun getCall(callId: String): LiveData<Call> {
        // TODO get call api.call
        return MutableLiveData(Call())
    }

    fun getAudioFile(callId: String) {
        api.callsControllerGetCallUrl(callId)
            .subscribe {
                processResponse(it)
            }
    }

    private fun processResponse(body: ResponseBody) {
        try {
            // todo change the file location/name according to your needs
            val futureStudioIconFile =
                File("FutureStudioIcon.png")
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
                    Log.d("TAG", "file download: $fileSizeDownloaded of $fileSize")
                }
                outputStream.flush()
                true
            } catch (e: IOException) {
                false
            } finally {
                inputStream?.close()
                outputStream?.close()
            }
        } catch (e: IOException) {
        }
    }

    fun getArticles(): LiveData<List<Article>> {
        return LiveDataReactiveStreams.fromPublisher(
            api.articlesControllerFindAll().toFlowable(BackpressureStrategy.BUFFER)
        )
    }

    fun togglePlayback() {
        isPlaying.value = !(isPlaying.value ?: true)
    }
}