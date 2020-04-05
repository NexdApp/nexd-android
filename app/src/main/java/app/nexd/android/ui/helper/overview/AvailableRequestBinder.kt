package app.nexd.android.ui.helper.overview

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import app.nexd.android.R
import kotlinx.android.synthetic.main.buyer_request_row.view.*
import mva2.adapter.ItemBinder
import mva2.adapter.ItemViewHolder

class AvailableRequestBinder: ItemBinder<HelperOverviewViewModel.AvailableRequestWrapper, AvailableRequestBinder.ViewHolder>() {

    class ViewHolder(itemView: View): ItemViewHolder<HelperOverviewViewModel.AvailableRequestWrapper>(itemView) {
        private val title: TextView = itemView.textView_title
        private val requestType: ImageView = itemView.imageView_requestType

        init {
            itemView.setOnClickListener {
                toggleItemSelection()
            }
        }

        fun bind(request: HelperOverviewViewModel.AvailableRequestWrapper) {
            title.text = itemView.context.getString(R.string.helper_request_overview_name_layout,
                request.requester.firstName,
                request.requester.lastName)
            if (request.type == HelperOverviewViewModel.RequestType.SHOPPING) {
                requestType.setImageResource(R.drawable.ic_shopping_basket_black_24dp)
            } else {
                requestType.setImageResource(R.drawable.ic_local_phone_black_24dp)
            }
        }
    }

    override fun bindViewHolder(holder: ViewHolder, item: HelperOverviewViewModel.AvailableRequestWrapper) {
        holder.bind(item)
    }

    override fun createViewHolder(parent: ViewGroup): ViewHolder {
        return ViewHolder(inflate(parent, R.layout.buyer_request_row))
    }

    override fun canBindData(item: Any): Boolean {
        return item is HelperOverviewViewModel.AvailableRequestWrapper
    }

}