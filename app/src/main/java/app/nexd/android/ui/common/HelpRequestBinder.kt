package app.nexd.android.ui.common

import android.view.View
import android.view.ViewGroup
import app.nexd.android.R
import app.nexd.android.api.model.HelpRequest
import app.nexd.android.ui.common.HelpRequestBinder.HelpRequestViewHolder
import kotlinx.android.synthetic.main.row_help_request.view.*
import mva2.adapter.ItemBinder
import mva2.adapter.ItemViewHolder

class HelpRequestBinder: ItemBinder<HelpRequest, HelpRequestViewHolder>() {

    class HelpRequestViewHolder(itemView: View): ItemViewHolder<HelpRequest>(itemView) {

        init {
            itemView.button_help_request.setOnClickListener {
                toggleItemSelection()
            }
        }

        fun bind(request: HelpRequest) {
            itemView.button_help_request.text = itemView.context.getString(R.string.user_name_layout,
                request.requester?.firstName,
                request.requester?.lastName)
        }
    }

    override fun bindViewHolder(holder: HelpRequestViewHolder, item: HelpRequest) {
        holder.bind(item)
    }

    override fun createViewHolder(parent: ViewGroup): HelpRequestViewHolder {
        return HelpRequestViewHolder(inflate(parent, R.layout.row_help_request))
    }

    override fun canBindData(item: Any): Boolean {
        return item is HelpRequest
    }

}