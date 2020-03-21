package de.andrestefanov.android.nearbuy.ui.buyer

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import de.andrestefanov.android.nearbuy.R
import kotlinx.android.synthetic.main.shopping_list_row.view.*
import mva2.adapter.ItemBinder
import mva2.adapter.ItemViewHolder

class ShoppingListEntryBinder: ItemBinder<ShoppingListViewModel.ShoppingListEntry, ShoppingListEntryBinder.ViewHolder>() {

    class ViewHolder(itemView: View) : ItemViewHolder<ShoppingListViewModel.ShoppingListEntry>(itemView) {
        private val name: TextView = itemView.name
        private val amount: TextView = itemView.amount

        fun bind(entry: ShoppingListViewModel.ShoppingListEntry) {
            name.text = entry.name
            amount.text = "${entry.amount}x"
        }
    }

    override fun bindViewHolder(holder: ViewHolder, item: ShoppingListViewModel.ShoppingListEntry) {
        holder.bind(item)
    }

    override fun createViewHolder(parent: ViewGroup): ViewHolder {
        return ViewHolder(inflate(parent, R.layout.shopping_list_row))
    }

    override fun canBindData(item: Any): Boolean {
        return item is ShoppingListViewModel.ShoppingListEntry
    }


}