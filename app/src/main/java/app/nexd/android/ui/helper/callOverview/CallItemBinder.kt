package app.nexd.android.ui.helper.callOverview

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import app.nexd.android.R
import kotlinx.android.synthetic.main.call_item_row.view.*
import mva2.adapter.ItemBinder
import mva2.adapter.ItemViewHolder

class CallItemBinder: ItemBinder<Call, CallItemBinder.ViewHolder>() {

    class ViewHolder(itemView: View) : ItemViewHolder<Call>(itemView) {
        private val title: TextView = itemView.textView_title

        init {
            itemView.setOnClickListener {
                toggleItemSelection()
            }
        }

        fun bind(item: Call) {
            title.text = item.username
        }
    }

    override fun bindViewHolder(holder: ViewHolder, item: Call) {
        holder.bind(item)
    }

    override fun createViewHolder(parent: ViewGroup): ViewHolder {
        return ViewHolder(inflate(parent, R.layout.call_item_row))
    }

    override fun canBindData(item: Any): Boolean {
        return item is Call
    }


}