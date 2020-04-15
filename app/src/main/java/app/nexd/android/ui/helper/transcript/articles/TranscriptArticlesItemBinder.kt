package app.nexd.android.ui.helper.transcript.articles

import android.view.LayoutInflater
import android.view.ViewGroup
import app.nexd.android.databinding.RowNewHelpRequestArticleBinding
import mva2.extension.DBItemBinder


class TranscriptArticlesItemBinder : DBItemBinder<TranscriptArticlesItemViewModel, RowNewHelpRequestArticleBinding>() {

    override fun canBindData(item: Any) = item is TranscriptArticlesItemViewModel

    override fun createBinding(parent: ViewGroup): RowNewHelpRequestArticleBinding {
        val inflater = LayoutInflater.from(parent.context)
        return RowNewHelpRequestArticleBinding.inflate(inflater, parent, false)
    }

    override fun bindModel(
        data: TranscriptArticlesItemViewModel?,
        binding: RowNewHelpRequestArticleBinding?
    ) {
        binding?.viewModel = data
    }

}