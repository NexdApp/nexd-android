package app.nexd.android.ui.utils

import android.net.Uri
import androidx.databinding.BindingAdapter
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.PlayerControlView
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util

@BindingAdapter("android:url")
fun PlayerControlView.url(url: String?) {
    if (url.isNullOrBlank()) {
        player = null
    } else {
        val player = SimpleExoPlayer.Builder(context).build()

        val dataSourceFactory: DataSource.Factory = DefaultDataSourceFactory(
            context,
            Util.getUserAgent(context, "nexd")
        )

        val audioSource: MediaSource = ProgressiveMediaSource.Factory(dataSourceFactory)
            .createMediaSource(Uri.parse(url))

        player.prepare(audioSource)

        this.player = player
    }
}