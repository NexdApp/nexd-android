package app.nexd.android.adapter

abstract class PlaybackInfoListener {

    enum class PlayerState {
        INVALID,
        PLAYING,
        PAUSED
    }

    open fun onDurationChanged(duration: Int) {}
    open fun onPositionChanged(position: Int) {}
    open fun onPlayerStateChanged(state: PlayerState) {}

}