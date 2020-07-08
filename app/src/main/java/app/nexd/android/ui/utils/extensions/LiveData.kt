package app.nexd.android.ui.utils.extensions

import androidx.annotation.NonNull
import androidx.lifecycle.*
import io.reactivex.BackpressureStrategy
import io.reactivex.Observable

fun <T> LiveData<T>.observe(owner: LifecycleOwner, observer: (T) -> Unit) {
    return this.observe(owner, Observer { observer(it) })
}

fun <T> Observable<T>.toLiveData(strategy: BackpressureStrategy): LiveData<T> {
    return LiveDataReactiveStreams.fromPublisher(
        toFlowable(strategy)
    )
}

fun <X, Y> LiveData<X>.map(@NonNull mapFunction : (X) -> Y): LiveData<Y> {
    return Transformations.map(this, mapFunction)
}

fun <X, Y> LiveData<X>.switchMap(@NonNull switchMapFunction : (X) -> LiveData<Y>): LiveData<Y> {
    return Transformations.switchMap(this, switchMapFunction)
}