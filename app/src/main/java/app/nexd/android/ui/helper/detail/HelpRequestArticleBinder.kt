package app.nexd.android.ui.helper.detail

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import app.nexd.android.R
import app.nexd.android.api.model.HelpRequestArticle
import app.nexd.android.ui.helper.detail.HelpRequestArticleBinder.ViewHolder
import kotlinx.android.synthetic.main.buyer_request_item_row.view.*
import mva2.adapter.ItemBinder
import mva2.adapter.ItemViewHolder

class HelpRequestArticleBinder : ItemBinder<HelpRequestArticle, ViewHolder>() {

    class ViewHolder(itemView: View) : ItemViewHolder<HelpRequestArticle>(itemView) {
        private val title: TextView = itemView.textView_title

        init {
            itemView.setOnClickListener {
                toggleItemSelection()
            }
        }

        fun bind(item: HelpRequestArticle) {
            title.text = itemView.context.getString(
                R.string.helper_request_detail_article_layout,
                item.articleCount,
                item.article.name
            )
        }
    }

    override fun bindViewHolder(holder: ViewHolder, item: HelpRequestArticle) {
        holder.bind(item)
    }

    override fun createViewHolder(parent: ViewGroup): ViewHolder {
        return ViewHolder(inflate(parent, R.layout.buyer_request_item_row))
    }

    override fun canBindData(item: Any): Boolean {
        return item is HelpRequestArticle
    }

}