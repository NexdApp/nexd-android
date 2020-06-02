package app.nexd.android.ui.common

import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import app.nexd.android.R
import app.nexd.android.ui.helper.requestOverview.HelperOverviewFragment
import mva2.adapter.ItemBinder
import mva2.adapter.ItemViewHolder

class HelpRequestAvailableHeaderBinder(private val clickListener: () -> Unit) :
    ItemBinder<HelperOverviewFragment.Header, HelpRequestAvailableHeaderBinder.ViewHolder>() {

    override fun bindViewHolder(
        holder: ViewHolder?,
        item: HelperOverviewFragment.Header?
    ) {
        holder!!.header.text = item!!.header
        if (item.header == holder.header.context.getString(R.string.helper_request_overview_heading_accepted_section)) {
            holder.button.visibility = View.GONE
        } else {
            holder.button.setOnClickListener { clickListener() }
        }
    }

    override fun createViewHolder(parent: ViewGroup?): ViewHolder {
        return ViewHolder(inflate(parent!!, R.layout.row_help_request_header))
    }

    override fun canBindData(item: Any?): Boolean {
        return item is HelperOverviewFragment.Header
    }

    class ViewHolder(itemView: View) :
        ItemViewHolder<HelperOverviewFragment.Header?>(itemView) {
        val header: TextView = itemView.findViewById(R.id.textView_header)
        val button: ImageButton = itemView.findViewById(R.id.button_nearRequests)
    }
}