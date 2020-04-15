package app.nexd.android.ui.common

import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import app.nexd.android.R
import app.nexd.android.api.model.Article
import kotlinx.android.synthetic.main.row_new_help_request_article.view.*
import mva2.adapter.ItemBinder
import mva2.adapter.ItemViewHolder

class HelpRequestCreateArticleBinder : ItemBinder<HelpRequestCreateArticleBinder.ArticleInput, HelpRequestCreateArticleBinder.ViewHolder>() {

    data class ArticleInput(val article: Article, var amount: Long = 0)

    class ViewHolder(itemView: View) : ItemViewHolder<ArticleInput>(itemView) {
        var name: TextView = itemView.textView_article_name
        var amount: EditText = itemView.editText_articleCount
    }

    override fun bindViewHolder(holder: ViewHolder, item: ArticleInput) {
        holder.amount.setText(item.amount.toString(), TextView.BufferType.EDITABLE)
        holder.name.text = item.article.name

        holder.amount.addTextChangedListener {
            val numberStr = holder.amount.text.toString()
            if (numberStr.length > 10) {// maximal 10 digits
                holder.amount.setText(item.amount.toString())
                holder.amount.setSelection(holder.amount.text.length)
            }else if (!numberStr.isBlank())
                item.amount = numberStr.toLong()
        }
        holder.amount.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                holder.amount.clearFocus()
            }
            false
        }
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