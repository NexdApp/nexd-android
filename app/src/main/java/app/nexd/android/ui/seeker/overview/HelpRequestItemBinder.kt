package app.nexd.android.ui.seeker.overview

import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import app.nexd.android.R
import app.nexd.android.api.model.HelpRequestArticle
import mva2.adapter.ItemBinder
import mva2.adapter.ItemViewHolder

class HelpRequestItemBinder() : ItemBinder<HelpRequestArticle, HelpRequestItemBinder.HelpRequestItemViewHolder>() {

    class HelpRequestItemViewHolder(itemView: View) : ItemViewHolder<HelpRequestArticle>(itemView) {
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
        item: HelpRequestArticle
    ) {
        holder.title.text = item.article.name
    }

    override fun canBindData(item: Any): Boolean {
        return item is HelpRequestArticle
    }
}