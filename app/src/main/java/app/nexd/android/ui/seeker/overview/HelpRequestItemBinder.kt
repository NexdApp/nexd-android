package app.nexd.android.ui.seeker.overview

import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import app.nexd.android.R
import app.nexd.android.api.model.Article
import app.nexd.android.api.model.RequestArticle
import mva2.adapter.ItemBinder
import mva2.adapter.ItemViewHolder

class HelpRequestItemBinder(private val articles: List<Article>) : ItemBinder<RequestArticle, HelpRequestItemBinder.HelpRequestItemViewHolder>() {

    class HelpRequestItemViewHolder(itemView: View) : ItemViewHolder<RequestArticle>(itemView) {
        var title: TextView = itemView.findViewById(R.id.request_list_item_title)

        init {
            itemView.setOnClickListener {
                toggleItemSelection()
            }

            itemView.findViewById<CheckBox>(R.id.request_list_item_checkbox).visibility = View.GONE
        }
    }

    override fun createViewHolder(parent: ViewGroup): HelpRequestItemViewHolder {
        return HelpRequestItemViewHolder(
            inflate(parent, R.layout.request_list_item_view)
        )
    }

    override fun bindViewHolder(
        holder: HelpRequestItemViewHolder,
        item: RequestArticle
    ) {
        holder.title.text = articles.first { item.articleId == it.id.toBigDecimal() }.name
    }

    override fun canBindData(item: Any): Boolean {
        return item is RequestArticle
    }
}