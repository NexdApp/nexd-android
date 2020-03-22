package de.andrestefanov.android.nearbuy.ui.seeker.create

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import de.andrestefanov.android.nearbuy.R
import de.andrestefanov.android.nearbuy.api.data.Article
import mva2.adapter.ItemBinder
import mva2.adapter.ItemViewHolder

class HelpRequestArticleBinder : ItemBinder<Article, HelpRequestArticleBinder.ViewHolder>() {

    class ViewHolder(itemView: View) : ItemViewHolder<Article>(itemView) {
        var name: TextView = itemView.findViewById(R.id.textview_article_name)
        var amount: TextView = itemView.findViewById(R.id.textview_amount)
        var increase: AppCompatButton = itemView.findViewById(R.id.button_increase)
        var decrease: AppCompatButton = itemView.findViewById(R.id.button_decrease)
    }

    override fun bindViewHolder(holder: ViewHolder, item: Article) {
        holder.amount.text = item.articleCount.toString()
        holder.name.text = item.name

        holder.increase.setOnClickListener {
            item.articleCount += 1
            holder.amount.text = item.articleCount.toString()
        }

        holder.decrease.setOnClickListener {
            item.articleCount = if (item.articleCount == 0L) 0L else item.articleCount - 1
            holder.amount.text = item.articleCount.toString()
        }
    }

    override fun createViewHolder(parent: ViewGroup): ViewHolder {
        return ViewHolder(
            inflate(parent, R.layout.new_help_request_article_list_item)
        )
    }

    override fun canBindData(item: Any?): Boolean {
        return item is Article
    }


}