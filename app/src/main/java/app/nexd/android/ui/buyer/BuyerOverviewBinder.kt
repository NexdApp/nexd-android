package app.nexd.android.ui.buyer

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import app.nexd.android.R
import app.nexd.android.api.model.RequestEntity
import app.nexd.android.ui.buyer.BuyerOverviewBinder.HelpRequestViewHolder
import kotlinx.android.synthetic.main.buyer_request_row.view.*
import mva2.adapter.ItemBinder
import mva2.adapter.ItemViewHolder

class BuyerOverviewBinder: ItemBinder<RequestEntity, HelpRequestViewHolder>() {

    class HelpRequestViewHolder(itemView: View): ItemViewHolder<RequestEntity>(itemView) {
        private val title: TextView = itemView.title

        init {
            itemView.setOnClickListener {
                toggleItemSelection()
            }
        }

        fun bind(request: RequestEntity) {
            title.text = """${request.requester?.firstName.toString()} ${request.requester?.lastName}"""
        }


    }

    override fun bindViewHolder(holder: HelpRequestViewHolder, item: RequestEntity) {
        holder.bind(item)
    }

    override fun createViewHolder(parent: ViewGroup): HelpRequestViewHolder {
        return HelpRequestViewHolder(inflate(parent, R.layout.buyer_request_row))
    }

    override fun canBindData(item: Any): Boolean {
        return item is RequestEntity
    }

}