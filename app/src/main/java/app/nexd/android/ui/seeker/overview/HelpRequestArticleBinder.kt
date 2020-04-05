package app.nexd.android.ui.seeker.overview

import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import app.nexd.android.R
import app.nexd.android.api.model.HelpRequestArticle
import mva2.adapter.ItemBinder
import mva2.adapter.ItemViewHolder

class HelpRequestArticleBinder : ItemBinder<HelpRequestArticle, HelpRequestArticleBinder.ViewModel>() {

    class ViewModel(itemView: View) : ItemViewHolder<HelpRequestArticle>(itemView) {
        var title: TextView = itemView.findViewById(R.id.request_list_item_title)

        init {
            itemView.setOnClickListener {
                toggleItemSelection()
            }
        }
    }

    override fun createViewHolder(parent: ViewGroup): ViewModel {
        return ViewModel(
            inflate(parent, R.layout.request_list_item_view)
        )
    }

    override fun bindViewHolder(
        holder: ViewModel,
        item: HelpRequestArticle
    ) {
        holder.title.text = """${item.articleCount}x ${item.article.name}"""
    }

    override fun canBindData(item: Any): Boolean {
        return item is HelpRequestArticle
    }
}