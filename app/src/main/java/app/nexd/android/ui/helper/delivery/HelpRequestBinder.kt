package app.nexd.android.ui.helper.delivery

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import app.nexd.android.R
import app.nexd.android.api.model.HelpRequest
import kotlinx.android.synthetic.main.view_request_delivery.view.*
import mva2.adapter.ItemBinder
import mva2.adapter.ItemViewHolder

class HelpRequestBinder: ItemBinder<HelpRequest, HelpRequestBinder.ViewHolder>() {

    class ViewHolder(itemView: View) : ItemViewHolder<HelpRequest>(itemView) {
        private val username: TextView = itemView.textView_username
        private val address: TextView = itemView.textView_address
        private val phoneNumber: TextView = itemView.textView_phoneNumber

        fun bind(request: HelpRequest) {
            username.text = request.requester?.lastName
            address.text = "%1 %2 %3".format(itemView.context.getString(R.string.delivery_request_address),
                request.street,
                request.number)
            phoneNumber.text = "%1 %2".format(itemView.context.getString(R.string.delivery_request_phoneNumber),
                request.phoneNumber)
        }
    }

    override fun bindViewHolder(holder: ViewHolder, item: HelpRequest) {
        holder.bind(item)
    }

    override fun createViewHolder(parent: ViewGroup): ViewHolder {
        return ViewHolder(inflate(parent, R.layout.view_request_delivery))
    }

    override fun canBindData(item: Any): Boolean {
        return item is HelpRequest
    }
}