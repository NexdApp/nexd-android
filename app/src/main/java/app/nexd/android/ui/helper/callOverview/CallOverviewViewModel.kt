package app.nexd.android.ui.helper.callOverview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CallOverviewViewModel : ViewModel() {

    fun getOpenCalls(): LiveData<List<Call>> {
        return MutableLiveData(
            listOf(
                Call(
                    0,
                    "Peter"
                )
            )
        )
    }

}