package app.nexd.android.ui.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import app.nexd.android.databinding.RowNewHelpRequestArticleBinding
import mva2.extension.DBItemBinder

class HelpRequestCreateArticleBinder :
    DBItemBinder<HelpRequestCreateArticleBinder.ArticleInput, RowNewHelpRequestArticleBinding>() {

    data class ArticleInput(
        val articleId: Long,
        val articleName: LiveData<String>,
        var amount: MutableLiveData<String>
    )

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