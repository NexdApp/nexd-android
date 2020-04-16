package app.nexd.android.ui.helper.transcript.articles

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class TranscriptArticlesItemViewModel(
    val articleId: Long,
    val articleName: LiveData<String>,
    val articleCount: MutableLiveData<String>
)