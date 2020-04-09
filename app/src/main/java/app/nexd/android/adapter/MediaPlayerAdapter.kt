package app.nexd.android.adapter

import android.media.MediaPlayer
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import java.lang.IllegalStateException
import java.util.concurrent.TimeUnit

class MediaPlayerAdapter {

    companion object {
        const val PLAYBACK_POSITION_REFRESH_INTERVAL_MS: Long = 100
    }

    private var mMediaPlayer: MediaPlayer? = null

    private var mTimeElapsedExecutor: Disposable? = null

    private var mPlaybackListener: PlaybackInfoListener? = null

    private var path: String? = null

    private fun initMediaPlayer() {
        if (mMediaPlayer == null) {
            mMediaPlayer = MediaPlayer()
            mMediaPlayer!!.setOnCompletionListener {
                reset()
            }
        }
    }

    fun loadMedia(path: String) {
        this.path = path
        initMediaPlayer()

        mMediaPlayer?.let {
            try {
                it.setDataSource(path)
            } catch (e: IllegalStateException) {
                Log.e("load()", e.toString())
            }

            try {
                it.prepare()
            } catch (e: IllegalStateException) {
                Log.e("prepare()", e.toString())
            }

            mPlaybackListener?.onDurationChanged(it.duration)
            mPlaybackListener?.onPositionChanged(0)
        }
    }

    fun isPlaying(): Boolean {
        return mMediaPlayer?.isPlaying ?: false
    }

    fun play() {
        mMediaPlayer?.let { mediaPlayer ->
            if (!mediaPlayer.isPlaying) {
                mediaPlayer.start()
                startUpdatingCallbackWithPosition()
                mPlaybackListener?.onPlayerStateChanged(PlaybackInfoListener.PlayerState.PLAYING)
            }
        }
    }

    fun pause() {
        mMediaPlayer?.let { mediaPlayer ->
            if (mediaPlayer.isPlaying) {
                mediaPlayer.pause()
                stopUpdateCallbackWithPosition(false)
                mPlaybackListener?.onPlayerStateChanged(PlaybackInfoListener.PlayerState.PAUSED)
            }
        }
    }

    fun reset() {
        mMediaPlayer?.let { mediaPlayer ->
            path?.let { path ->
                mediaPlayer.reset()
                loadMedia(path)
                stopUpdateCallbackWithPosition(true)
                mPlaybackListener?.onPlayerStateChanged(PlaybackInfoListener.PlayerState.PAUSED)
            }
        }
    }

    fun seekTo(ms: Int) {
        mMediaPlayer?.seekTo(ms)
    }

    fun setPlaybackListener(playbackInfoListener: PlaybackInfoListener) {
        this.mPlaybackListener = playbackInfoListener
    }

    private fun startUpdatingCallbackWithPosition() {
        mTimeElapsedExecutor = AndroidSchedulers
            .mainThread()
            .schedulePeriodicallyDirect({
                Log.d("update", "updat")
                updateProgressCallbackTask()
            }, 0,  PLAYBACK_POSITION_REFRESH_INTERVAL_MS, TimeUnit.MILLISECONDS)
    }

    private fun stopUpdateCallbackWithPosition(resetUIPosition: Boolean) {
        mTimeElapsedExecutor?.dispose()
        mTimeElapsedExecutor = null
        if (resetUIPosition) {
            mPlaybackListener?.onPositionChanged(0)
        }
    }

    private fun updateProgressCallbackTask() {
        mMediaPlayer?.let {
            if (it.isPlaying)
                mPlaybackListener?.onPositionChanged(it.currentPosition)
        }
    }



}