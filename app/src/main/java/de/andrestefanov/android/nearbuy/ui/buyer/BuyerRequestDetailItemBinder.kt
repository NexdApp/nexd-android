package de.andrestefanov.android.nearbuy.ui.buyer

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import de.andrestefanov.android.nearbuy.R
import de.andrestefanov.android.nearbuy.api.model.RequestArticle
import de.andrestefanov.android.nearbuy.ui.buyer.BuyerRequestDetailItemBinder.RequestItemViewHolder
import kotlinx.android.synthetic.main.buyer_request_item_row.view.*
import mva2.adapter.ItemBinder
import mva2.adapter.ItemViewHolder

class BuyerRequestDetailItemBinder: ItemBinder<BuyerRequestDetailItemBinder.RequestArticleViewData, RequestItemViewHolder>() {

    data class RequestArticleViewData(
        val name: String,
        val requestArticle: RequestArticle
    )

    class RequestItemViewHolder(itemView: View) : ItemViewHolder<RequestArticleViewData>(itemView) {
        private val title: TextView = itemView.title

        init {
            itemView.setOnClickListener {
                toggleItemSelection()
            }
        }

        fun bind(item: RequestArticleViewData) {
            title.text = """${item.requestArticle.articleCount} x ${item.name}"""
        }
    }

    override fun bindViewHolder(holder: RequestItemViewHolder, item: RequestArticleViewData) {
        holder.bind(item)
    }

    override fun createViewHolder(parent: ViewGroup): RequestItemViewHolder {
        return RequestItemViewHolder(inflate(parent, R.layout.buyer_request_item_row))
    }

    override fun canBindData(item: Any): Boolean {
        return item is RequestArticleViewData
    }

}