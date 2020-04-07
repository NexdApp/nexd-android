package app.nexd.android.ui.common

import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import app.nexd.android.R
import app.nexd.android.api.model.Article
import kotlinx.android.synthetic.main.row_new_help_request_article.view.*
import mva2.adapter.ItemBinder
import mva2.adapter.ItemViewHolder

class HelpRequestCreateArticleBinder : ItemBinder<HelpRequestCreateArticleBinder.ArticleInput, HelpRequestCreateArticleBinder.ViewHolder>() {

    data class ArticleInput(val article: Article, var amount: Long = 0)

    class ViewHolder(itemView: View) : ItemViewHolder<ArticleInput>(itemView) {
        var name: TextView = itemView.textView_article_name
        var amount: EditText = itemView.textView_amount
        /*var increase: ImageButton = itemView.button_increase
        var decrease: ImageButton = itemView.button_decrease*/
    }

    override fun bindViewHolder(holder: ViewHolder, item: ArticleInput) {
        holder.amount.setText(item.amount.toString(), TextView.BufferType.EDITABLE)
        holder.name.text = item.article.name

        /*holder.increase.setOnClickListener {
            item.amount += 1
            holder.amount.setText(item.amount.toString(), TextView.BufferType.EDITABLE)
        }*/

        holder.amount.setOnEditorActionListener { _, _, _ ->
            val numberStr = holder.amount.text.toString()
            if (!numberStr.isBlank())
                item.amount = numberStr.toLong()
            false
        }

        /*holder.decrease.setOnClickListener {
            item.amount = if (item.amount == 0.toLong()) 0 else item.amount - 1
            holder.amount.setText(item.amount.toString(), TextView.BufferType.EDITABLE)
        }*/
    }

    override fun createViewHolder(parent: ViewGroup): ViewHolder {
        return ViewHolder(
            inflate(parent, R.layout.row_new_help_request_article)
        )
    }

    override fun canBindData(item: Any?): Boolean {
        return item is ArticleInput
    }


}