package app.nexd.android.ui.seeker.phonecall

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import app.nexd.android.api
import io.reactivex.BackpressureStrategy

class PhoneCallViewModel : ViewModel() {

    fun getPhoneNumber(): LiveData<String> {
        return LiveDataReactiveStreams.fromPublisher(
            api.callsControllerGetNumber()
                .map {
                    // temporary fix due to wrong api return
                    val splittedPhonenumber = it.split(":")
                    if (splittedPhonenumber.size != 2)
                        return@map ""
                    else {
                        splittedPhonenumber[1].replace("\"", "")
                            .replace("}","")
                    }
                }
                .toFlowable(BackpressureStrategy.LATEST)
        )
    }

}