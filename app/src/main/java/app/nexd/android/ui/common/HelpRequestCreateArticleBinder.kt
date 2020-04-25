package app.nexd.android.ui.common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import app.nexd.android.api.model.Article
import app.nexd.android.databinding.RowNewHelpRequestArticleBinding
import kotlinx.android.synthetic.main.fragment_helper_request_detail.view.*
import kotlinx.android.synthetic.main.row_new_help_request_article.view.*
import mva2.adapter.ItemViewHolder
import mva2.extension.DBItemBinder

class HelpRequestCreateArticleBinder :
    DBItemBinder<HelpRequestCreateArticleBinder.ArticleInput, RowNewHelpRequestArticleBinding>() {

    data class ArticleInput(
        val articleId: Long,
        val articleName: LiveData<String>,
        var amount: MutableLiveData<String>
    )

    // class ViewHolder(itemView: View) : ItemViewHolder<ArticleInput>(itemView)


    /*override fun bindViewHolder(holder: ViewHolder, item: ArticleInput) {
        item.view

        holder.amount.setText(item.amount.toString(), TextView.BufferType.EDITABLE)
        holder.name.text = item.article.name

        holder.amount.addTextChangedListener {
            val numberStr = holder.amount.text.toString()
            if (numberStr.length > 10) {// maximal 10 digits
                holder.amount.setText(item.amount.toString())
                holder.amount.setSelection(holder.amount.text.length)
            } else if (!numberStr.isBlank())
                item.amount = numberStr.toLong()
        }
        holder.amount.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                holder.amount.clearFocus()
            }
            false
        }
    }*/

    override fun canBindData(item: Any?): Boolean {
        return item is ArticleInput
    }

    override fun createBinding(parent: ViewGroup): RowNewHelpRequestArticleBinding {
        val inflater = LayoutInflater.from(parent.context)
        return RowNewHelpRequestArticleBinding.inflate(inflater, parent, false)
    }

    override fun bindModel(data: ArticleInput, binding: RowNewHelpRequestArticleBinding) {
        binding.viewModel = data
    }


}