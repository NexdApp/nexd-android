package app.nexd.android.ui.helper.checkout

import android.view.View
import android.view.ViewGroup
import app.nexd.android.R
import app.nexd.android.api.model.RequestArticle
import kotlinx.android.synthetic.main.shopping_list_row.view.*
import mva2.adapter.ItemBinder
import mva2.adapter.ItemViewHolder

class CheckoutItemBinder: ItemBinder<RequestArticle, CheckoutItemBinder.ViewHolder>() {

    class ViewHolder(itemView: View): ItemViewHolder<RequestArticle>(itemView) {

        fun bind(item: RequestArticle) {
            itemView.name.text = item.articleId.toString()
            itemView.amount.text = "${item.articleCount}x"
            itemView.checked.isChecked = item.articleDone
            itemView.checked.isEnabled = false
        }
    }

    override fun bindViewHolder(holder: ViewHolder, item: RequestArticle) {
        holder.bind(item)
    }

    override fun createViewHolder(parent: ViewGroup): ViewHolder {
        return ViewHolder(
            inflate(parent, R.layout.shopping_list_row)
        )
    }

    override fun canBindData(item: Any): Boolean {
        return item is RequestArticle
    }
}