package app.nexd.android.ui.seeker.create

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import app.nexd.android.R
import app.nexd.android.api.model.Article
import mva2.adapter.ItemBinder
import mva2.adapter.ItemViewHolder

class HelpRequestArticleBinder : ItemBinder<HelpRequestArticleBinder.ArticleInput, HelpRequestArticleBinder.ViewHolder>() {

    data class ArticleInput(val article: Article, var amount: Int = 0)

    class ViewHolder(itemView: View) : ItemViewHolder<ArticleInput>(itemView) {
        var name: TextView = itemView.findViewById(R.id.textview_article_name)
        var amount: TextView = itemView.findViewById(R.id.textview_amount)
        var increase: AppCompatButton = itemView.findViewById(R.id.button_increase)
        var decrease: AppCompatButton = itemView.findViewById(R.id.button_decrease)
    }

    override fun bindViewHolder(holder: ViewHolder, item: ArticleInput) {
        holder.amount.text = item.amount.toString()
        holder.name.text = item.article.name

        holder.increase.setOnClickListener {
            item.amount += 1
            holder.amount.text = item.amount.toString()
        }

        holder.decrease.setOnClickListener {
            item.amount = if (item.amount == 0) 0 else item.amount - 1
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