package de.andrestefanov.android.nearbuy.ui.seeker

import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import de.andrestefanov.android.nearbuy.R
import de.andrestefanov.android.nearbuy.model.data.HelpRequestItem
import mva2.adapter.ItemBinder
import mva2.adapter.ItemViewHolder

class HelpRequestItemBinder : ItemBinder<HelpRequestItem, HelpRequestItemBinder.HelpRequestItemViewHolder>() {

    class HelpRequestItemViewHolder(itemView: View) : ItemViewHolder<HelpRequestItem>(itemView) {
        var title: TextView = itemView.findViewById(R.id.request_list_item_title)

        init {
            itemView.findViewById<CheckBox>(R.id.request_list_item_checkbox).visibility = View.GONE
        }
    }

    override fun createViewHolder(parent: ViewGroup): HelpRequestItemViewHolder {
        return HelpRequestItemViewHolder(inflate(parent, R.layout.request_list_item_view))
    }

    override fun bindViewHolder(
        holder: HelpRequestItemViewHolder,
        item: HelpRequestItem
    ) {
        holder.title.text = item.name
    }

    override fun canBindData(item: Any): Boolean {
        return item is HelpRequestItem
    }
}