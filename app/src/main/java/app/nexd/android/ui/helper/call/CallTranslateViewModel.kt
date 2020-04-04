package app.nexd.android.ui.helper.call

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.nexd.android.R

class CallTranslateViewModel(application: Application) : AndroidViewModel(application) {

    val isPlaying = MutableLiveData(false)
    // val playbackIcon = MutableLiveData(application.getDrawable(R.drawable.ic_play_arrow_black_24dp))


    fun togglePlayback() {
        isPlaying.value = !(isPlaying.value ?: true)
    }
}