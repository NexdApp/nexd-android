package app.nexd.android.ui.seeker.call

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
                    val phoneNumber = it.split(":")
                    if (phoneNumber.size != 2)
                        return@map ""
                    else {
                        phoneNumber[1].replace("\"", "")
                            .replace("}","")
                    }
                }
                .toFlowable(BackpressureStrategy.LATEST)
        )
    }

}