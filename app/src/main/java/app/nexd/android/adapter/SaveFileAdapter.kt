package app.nexd.android.adapter

import okhttp3.ResponseBody
import java.io.*

class SaveFileAdapter {

    abstract class SaveFileListener {
        open fun onDownloadFinished() {}
        open fun onDownloadStarted() {}
        open fun onDownloadProgress(progress: Long) {}
    }

    private var mListener: SaveFileListener? = null

    fun saveIntoFile(body: ResponseBody, outputFile: File): String? {
        return try {
            if (!outputFile.exists())
                outputFile.createNewFile()

            var inputStream: InputStream? = null
            var outputStream: OutputStream? = null
            try {
                val fileReader = ByteArray(4096)
                val fileSize = body.contentLength()
                var fileSizeDownloaded: Long = 0
                inputStream = body.byteStream()
                outputStream = FileOutputStream(outputFile)
                mListener?.onDownloadStarted()
                while (true) {
                    val read: Int = inputStream.read(fileReader)
                    if (read == -1) {
                        break
                    }
                    outputStream.write(fileReader, 0, read)
                    fileSizeDownloaded += read.toLong()
                    mListener?.onDownloadProgress(fileSizeDownloaded * 100 / fileSize)
                }
                mListener?.onDownloadFinished()
                outputStream.flush()
                outputFile.absolutePath
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

    fun setListener(listener: SaveFileListener) {
        mListener = listener
    }
}