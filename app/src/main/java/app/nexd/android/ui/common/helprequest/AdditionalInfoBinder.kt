package app.nexd.android.ui.common.helprequest

import android.view.LayoutInflater
import android.view.ViewGroup
import app.nexd.android.databinding.ViewEdittextHelpRequestAdditionalInfoBinding
import app.nexd.android.ui.seeker.create.SeekerCreateRequestViewModel.AdditionalInformation
import mva2.extension.DBItemBinder

class AdditionalInfoBinder : DBItemBinder<AdditionalInformation, ViewEdittextHelpRequestAdditionalInfoBinding>() {

    override fun canBindData(item: Any): Boolean {
        return item is AdditionalInformation
    }

    override fun createBinding(parent: ViewGroup): ViewEdittextHelpRequestAdditionalInfoBinding {
        val inflater = LayoutInflater.from(parent.context)
        return ViewEdittextHelpRequestAdditionalInfoBinding.inflate(inflater, parent, false)
    }

    override fun bindModel(data: AdditionalInformation, binding: ViewEdittextHelpRequestAdditionalInfoBinding) {
        binding.value = data
    }

}