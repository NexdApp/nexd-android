package app.nexd.android.ui.common

import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import app.nexd.android.R
import app.nexd.android.ui.helper.requestOverview.HelperOverviewFragment
import mva2.adapter.ItemBinder
import mva2.adapter.ItemViewHolder

class ExpandableHeaderItemBinder :
    ItemBinder<HelperOverviewFragment.Header, ExpandableHeaderItemBinder.ViewHolder>() {

    override fun bindViewHolder(
        holder: ViewHolder?,
        item: HelperOverviewFragment.Header?
    ) {
        holder!!.header.text = item!!.header
        holder.toggle.text = if (holder.isSectionExpanded) "Collapse" else "Expand"
    }

    override fun createViewHolder(parent: ViewGroup?): ViewHolder {
        return ViewHolder(inflate(parent!!, R.layout.row_help_request_header))
    }

    override fun canBindData(item: Any?): Boolean {
        return item is HelperOverviewFragment.Header
    }

    class ViewHolder(itemView: View) :
        ItemViewHolder<HelperOverviewFragment.Header?>(itemView) {
        val header: TextView
        val toggle: Button

        init {
            toggle = itemView.findViewById(R.id.btn_toggle)
            header = itemView.findViewById(R.id.tv_header)
            toggle.setOnClickListener { v: View? -> toggleSectionExpansion() }
        }
    }
}