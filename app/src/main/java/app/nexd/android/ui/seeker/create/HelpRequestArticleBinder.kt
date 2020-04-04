package app.nexd.android.ui.seeker.create

import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import app.nexd.android.R
import app.nexd.android.api.model.Article
import kotlinx.android.synthetic.main.new_help_request_article_list_item.view.*
import mva2.adapter.ItemBinder
import mva2.adapter.ItemViewHolder

class HelpRequestArticleBinder : ItemBinder<HelpRequestArticleBinder.ArticleInput, HelpRequestArticleBinder.ViewHolder>() {

    data class ArticleInput(val article: Article, var amount: Long = 0)

    class ViewHolder(itemView: View) : ItemViewHolder<ArticleInput>(itemView) {
        var name: TextView = itemView.textView_article_name
        var amount: TextView = itemView.textView_amount
        var increase: ImageButton = itemView.button_increase
        var decrease: ImageButton = itemView.button_decrease
    }

    override fun bindViewHolder(holder: ViewHolder, item: ArticleInput) {
        holder.amount.text = item.amount.toString()
        holder.name.text = item.article.name

        holder.increase.setOnClickListener {
            item.amount += 1
            holder.amount.text = item.amount.toString()
        }

        holder.decrease.setOnClickListener {
            item.amount = if (item.amount == 0.toLong()) 0 else item.amount - 1
            holder.amount.text = item.amount.toString()
        }
    }

    override fun createViewHolder(parent: ViewGroup): ViewHolder {
        return ViewHolder(
            inflate(parent, R.layout.new_help_request_article_list_item)
        )
    }

    override fun canBindData(item: Any?): Boolean {
        return item is ArticleInput
    }


}