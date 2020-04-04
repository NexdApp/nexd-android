package app.nexd.android.ui.helper.overview

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import app.nexd.android.R
import app.nexd.android.api.model.RequestEntity
import app.nexd.android.ui.helper.overview.HelperOverviewBinder.HelpRequestViewHolder
import kotlinx.android.synthetic.main.buyer_request_row.view.*
import mva2.adapter.ItemBinder
import mva2.adapter.ItemViewHolder

class HelperOverviewBinder: ItemBinder<RequestEntity, HelpRequestViewHolder>() {

    class HelpRequestViewHolder(itemView: View): ItemViewHolder<RequestEntity>(itemView) {
        private val title: TextView = itemView.textView_title
        private val requestType: ImageView = itemView.imageView_requestType

        init {
            itemView.setOnClickListener {
                toggleItemSelection()
            }
        }

        fun bind(request: RequestEntity) {
            title.text = "%1 %2".format(request.requester!!.firstName, request.requester!!.lastName)
            if (true) {
                requestType.setImageResource(R.drawable.ic_shopping_basket_black_24dp)
            } else {
                requestType.setImageResource(R.drawable.ic_local_phone_black_24dp)
            }
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