package app.nexd.android.ui.helper.shoppingList

import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import app.nexd.android.R
import kotlinx.android.synthetic.main.shopping_list_row.view.*
import mva2.adapter.ItemBinder
import mva2.adapter.ItemViewHolder

class ShoppingListEntryBinder: ItemBinder<ShoppingListViewModel.ShoppingListEntry, ShoppingListEntryBinder.ViewHolder>() {

    class ViewHolder(itemView: View) : ItemViewHolder<ShoppingListViewModel.ShoppingListEntry>(itemView) {
        private val name: TextView = itemView.name
        private val amount: TextView = itemView.amount
        private val collect: CheckBox = itemView.checked

        fun bind(entry: ShoppingListViewModel.ShoppingListEntry) {
            name.text = entry.articleName
            amount.text = itemView.context.getString(R.string.helper_shooping_list_amount_layout,
                entry.articleAmount)
            collect.isChecked = entry.isCollected

            collect.setOnCheckedChangeListener { _, isChecked ->
                entry.isCollected = isChecked
                itemView.post {
                    toggleItemSelection()
                }
            }

            itemView.setOnClickListener {
                collect.isChecked = !collect.isChecked
            }
        }
    }

    override fun bindViewHolder(holder: ViewHolder, item: ShoppingListViewModel.ShoppingListEntry) {
        holder.bind(item)
    }

    override fun createViewHolder(parent: ViewGroup): ViewHolder {
        return ViewHolder(
            inflate(parent, R.layout.shopping_list_row)
        )
    }

    override fun canBindData(item: Any): Boolean {
        return item is ShoppingListViewModel.ShoppingListEntry
    }


}