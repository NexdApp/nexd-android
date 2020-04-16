package app.nexd.android.ui.utils.livedata;

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> LiveData<T>.observe(owner: LifecycleOwner, observer: (T) -> Unit) {
    return this.observe(owner, Observer { observer(it) })
}