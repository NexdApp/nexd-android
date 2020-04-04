package app.nexd.android.ui.helper.overview

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import app.nexd.android.R
import app.nexd.android.api.model.HelpRequest
import app.nexd.android.ui.helper.overview.HelpRequestBinder.HelpRequestViewHolder
import kotlinx.android.synthetic.main.buyer_request_row.view.*
import mva2.adapter.ItemBinder
import mva2.adapter.ItemViewHolder

class HelpRequestBinder: ItemBinder<HelpRequest, HelpRequestViewHolder>() {

    class HelpRequestViewHolder(itemView: View): ItemViewHolder<HelpRequest>(itemView) {
        private val title: TextView = itemView.textView_title

        init {
            itemView.setOnClickListener {
                toggleItemSelection()
            }
        }

        fun bind(request: HelpRequest) {
            title.text = itemView.context.getString(R.string.helper_request_overview_name_layout,
                request.requester!!.firstName,
                request.requester!!.lastName)
        }
    }

    override fun bindViewHolder(holder: HelpRequestViewHolder, item: HelpRequest) {
        holder.bind(item)
    }

    override fun createViewHolder(parent: ViewGroup): HelpRequestViewHolder {
        return HelpRequestViewHolder(inflate(parent, R.layout.buyer_request_row))
    }

    override fun canBindData(item: Any): Boolean {
        return item is HelpRequest
    }

}