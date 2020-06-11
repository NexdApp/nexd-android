package app.nexd.android.ui.helper.delivery

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import app.nexd.android.R
import app.nexd.android.api.model.HelpRequest
import kotlinx.android.synthetic.main.row_helper_delivery_help_request.view.*
import mva2.adapter.ItemBinder
import mva2.adapter.ItemViewHolder

class DeliveryHelpRequestBinder : ItemBinder<HelpRequest, DeliveryHelpRequestBinder.ViewHolder>() {

    class ViewHolder(itemView: View) : ItemViewHolder<HelpRequest>(itemView) {
        private val username: TextView = itemView.textView_username
        private val address: TextView = itemView.textView_address
        private val phoneNumber: TextView = itemView.textView_phoneNumber

        fun bind(request: HelpRequest) {
            username.text = itemView.context.getString(
                R.string.user_name_layout,
                request.firstName,
                request.lastName
            )
            address.text = itemView.context.getString(
                R.string.helper_delivery_address_layout,
                request.street ?: "--",
                request.number ?: "--",
                request.zipCode ?: "--",
                request.city ?: "--"
            )

            phoneNumber.text = request.phoneNumber ?: "--"
        }
    }

    override fun bindViewHolder(holder: ViewHolder, item: HelpRequest) {
        holder.bind(item)
    }

    override fun createViewHolder(parent: ViewGroup): ViewHolder {
        return ViewHolder(inflate(parent, R.layout.row_helper_delivery_help_request))
    }

    override fun canBindData(item: Any): Boolean {
        return item is HelpRequest
    }
}