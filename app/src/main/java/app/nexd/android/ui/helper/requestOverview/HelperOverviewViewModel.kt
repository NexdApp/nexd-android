package app.nexd.android.ui.helper.requestOverview

import android.annotation.SuppressLint
import android.app.Application
import android.text.SpannableString
import android.text.Spanned
import android.text.style.RelativeSizeSpan
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import app.nexd.android.R
import app.nexd.android.api
import app.nexd.android.api.model.HelpList
import app.nexd.android.api.model.HelpRequest
import app.nexd.android.api.model.HelpRequestStatus
import app.nexd.android.ui.common.Constants.Companion.MAXIMAL_ACCEPTED_REQUESTS
import app.nexd.android.ui.common.Constants.Companion.USER_ME
import io.reactivex.android.schedulers.AndroidSchedulers

class HelperOverviewViewModel(application: Application) : AndroidViewModel(application) {

    sealed class Progress {
        object Idle : Progress()
        object Loading : Progress()
        class ZipCodeDialog(val zipCode: String): Progress()
        class Error(val message: String) : Progress()
    }

    var zipCode: String? = null
        private set

    val myAcceptedRequestsTitle = MutableLiveData<SpannableString>()
    val myAcceptedRequests = MutableLiveData(emptyList<HelpRequest>())

    val openRequestsTitle = MutableLiveData<SpannableString>()
    val openRequests = MutableLiveData(emptyList<HelpRequest>())

    val progress = MutableLiveData<Progress>(Progress.Idle)

    init {
        loadMyAcceptedRequests()
        loadNearOpenRequests()
        with(api) {
            userControllerFindMe()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    filterbyZipCode(it.zipCode)
                }, {

                })
        }
    }

    // mb different way to get active requests??
    @SuppressLint("CheckResult")
    fun loadMyAcceptedRequests() {
        progress.value = Progress.Loading
        api.helpListsControllerGetUserLists(null)
            .map { helpLists ->
                helpLists.filter { it.status == HelpList.StatusEnum.ACTIVE }
                    .maxBy { it.createdAt }?.helpRequests
                    .orEmpty()
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                progress.value = Progress.Idle

                val acceptedTitle = getApplication<Application>().resources.getString(
                        R.string.helper_request_overview_heading_accepted_section)
                val acceptedInfo = getApplication<Application>().resources.getString(
                    R.string.helper_request_overview_heading_accepted_section_counter,
                    it.size,
                    MAXIMAL_ACCEPTED_REQUESTS
                )
                val accepted = SpannableString(acceptedTitle + acceptedInfo)
                accepted.setSpan(
                    RelativeSizeSpan(0.5f), acceptedTitle.length,
                    acceptedTitle.length + acceptedInfo.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                myAcceptedRequestsTitle.value = accepted
                myAcceptedRequests.value = it
            }, {
                progress.value = Progress.Error(it.message.toString())
            })
    }

    @SuppressLint("CheckResult")
    fun loadNearOpenRequests() {
        progress.value = Progress.Loading
        api
            .helpRequestsControllerGetAll(
                USER_ME,
                true,
                listOf(zipCode),
                true,
                listOf(
                    HelpRequestStatus.PENDING
                )
            )
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                progress.value = Progress.Idle

                val nearTitle = getApplication<Application>()
                    .applicationContext
                    .getString(R.string.helper_request_overview_heading_available_section)
                var nearSubtitle = ""
                zipCode?.let { zipCode ->
                    nearSubtitle = getApplication<Application>()
                        .applicationContext
                        .getString(
                            R.string.helper_request_overview_heading_open_section_zip,
                            zipCode
                        )
                }
                val near = SpannableString(nearTitle + nearSubtitle)
                near.setSpan(
                    RelativeSizeSpan(0.5f), nearTitle.length,
                    nearTitle.length + nearSubtitle.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )

                openRequestsTitle.value = near
                openRequests.value = it
            }, {
                progress.value = Progress.Error(it.message.toString())
            })
    }

    fun editZipCode() {
        progress.value = Progress.ZipCodeDialog(zipCode ?: "")
    }

    fun filterbyZipCode(zipCode: String?) {
        progress.value = Progress.Idle
        this.zipCode = if (zipCode.isNullOrBlank()) null else zipCode
        loadNearOpenRequests()
    }
}
