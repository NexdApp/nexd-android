package de.andrestefanov.android.nearbuy.ui.buyer

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import de.andrestefanov.android.nearbuy.R
import de.andrestefanov.android.nearbuy.api.data.HelpRequestItem
import de.andrestefanov.android.nearbuy.ui.buyer.BuyerDetailItemBinder.RequestItemViewHolder
import kotlinx.android.synthetic.main.buyer_request_item_row.view.*
import mva2.adapter.ItemBinder
import mva2.adapter.ItemViewHolder

class BuyerDetailItemBinder: ItemBinder<HelpRequestItem, RequestItemViewHolder>() {

    class RequestItemViewHolder(itemView: View) : ItemViewHolder<HelpRequestItem>(itemView) {
        private val title: TextView = itemView.title

        init {
            itemView.setOnClickListener {
                toggleItemSelection()
            }
        }

        fun bind(item: HelpRequestItem) {
            title.text = item.name
        }
    }

    override fun bindViewHolder(holder: RequestItemViewHolder, item: HelpRequestItem) {
        holder.bind(item)
    }

    override fun createViewHolder(parent: ViewGroup): RequestItemViewHolder {
        return RequestItemViewHolder(inflate(parent, R.layout.buyer_request_item_row))
    }

    override fun canBindData(item: Any): Boolean {
        return item is HelpRequestItem
    }

}