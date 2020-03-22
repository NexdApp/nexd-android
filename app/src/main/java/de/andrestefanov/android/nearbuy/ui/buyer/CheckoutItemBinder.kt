package de.andrestefanov.android.nearbuy.ui.buyer

import android.view.View
import android.view.ViewGroup
import de.andrestefanov.android.nearbuy.R
import de.andrestefanov.android.nearbuy.api.data.HelpRequestItem
import kotlinx.android.synthetic.main.shopping_list_row.view.*
import mva2.adapter.ItemBinder
import mva2.adapter.ItemViewHolder

class CheckoutItemBinder: ItemBinder<HelpRequestItem, CheckoutItemBinder.ViewHolder>() {

    class ViewHolder(itemView: View): ItemViewHolder<HelpRequestItem>(itemView) {

        fun bind(item: HelpRequestItem) {
            itemView.name.text = item.name
            itemView.amount.text = "${item.amount}x"
            itemView.checked.isChecked = item.collected
            itemView.checked.isEnabled = false
        }
    }

    override fun bindViewHolder(holder: ViewHolder, item: HelpRequestItem) {
        holder.bind(item)
    }

    override fun createViewHolder(parent: ViewGroup): ViewHolder {
        return ViewHolder(inflate(parent, R.layout.shopping_list_row))
    }

    override fun canBindData(item: Any): Boolean {
        return item is HelpRequestItem
    }
}