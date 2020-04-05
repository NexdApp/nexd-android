package app.nexd.android.ui.helper.checkout

import android.view.View
import android.view.ViewGroup
import app.nexd.android.R
import app.nexd.android.api.model.HelpRequestArticle
import kotlinx.android.synthetic.main.shopping_list_row.view.*
import mva2.adapter.ItemBinder
import mva2.adapter.ItemViewHolder

class HelpRequestArticleBinder: ItemBinder<HelpRequestArticle, HelpRequestArticleBinder.ViewHolder>() {

    class ViewHolder(itemView: View): ItemViewHolder<HelpRequestArticle>(itemView) {

        fun bind(item: HelpRequestArticle) {
            itemView.name.text = item.article?.name
            itemView.amount.text = item.articleCount.toString()
            itemView.checked.isChecked = item.articleDone ?: false
            itemView.checked.isEnabled = false
        }
    }

    override fun bindViewHolder(holder: ViewHolder, item: HelpRequestArticle) {
        holder.bind(item)
    }

    override fun createViewHolder(parent: ViewGroup): ViewHolder {
        return ViewHolder(
            inflate(parent, R.layout.shopping_list_row)
        )
    }

    override fun canBindData(item: Any): Boolean {
        return item is HelpRequestArticle
    }
}