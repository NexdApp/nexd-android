package de.andrestefanov.android.nearbuy.ui.buyer

import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import de.andrestefanov.android.nearbuy.R
import de.andrestefanov.android.nearbuy.api.data.ShoppingList
import kotlinx.android.synthetic.main.shopping_list_row.view.*
import mva2.adapter.ItemBinder
import mva2.adapter.ItemViewHolder

class ShoppingListEntryBinder: ItemBinder<ShoppingList.Entry, ShoppingListEntryBinder.ViewHolder>() {

    class ViewHolder(itemView: View) : ItemViewHolder<ShoppingList.Entry>(itemView) {
        private val name: TextView = itemView.name
        private val amount: TextView = itemView.amount
        private val collect: CheckBox = itemView.checked

        fun bind(entry: ShoppingList.Entry) {
            name.text = entry.name
            amount.text = "${entry.amount}x"
            collect.isChecked = entry.requestItems[0].collected

            itemView.setOnClickListener {
                collect.isChecked = !collect.isChecked
            }
            name.setOnClickListener {
                collect.isChecked = !collect.isChecked
            }
            amount.setOnClickListener {
                collect.isChecked = !collect.isChecked
            }
            collect.setOnCheckedChangeListener { _, isChecked ->
                entry.setCollected(isChecked)
            }
        }
    }

    override fun bindViewHolder(holder: ViewHolder, item: ShoppingList.Entry) {
        holder.bind(item)
    }

    override fun createViewHolder(parent: ViewGroup): ViewHolder {
        return ViewHolder(inflate(parent, R.layout.shopping_list_row))
    }

    override fun canBindData(item: Any): Boolean {
        return item is ShoppingList.Entry
    }


}