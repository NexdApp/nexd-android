package app.nexd.android.ui.helper.checkout

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.nexd.android.R
import app.nexd.android.api.model.HelpRequest
import app.nexd.android.api.model.HelpRequestArticle
import kotlinx.android.synthetic.main.view_checkout_request.view.*
import mva2.adapter.ItemBinder
import mva2.adapter.ItemViewHolder
import mva2.adapter.ListSection
import mva2.adapter.MultiViewAdapter

class HelpRequestBinder: ItemBinder<HelpRequest, HelpRequestBinder.ViewModel>() {

    class ViewModel(itemView: View) : ItemViewHolder<HelpRequest>(itemView) {
        private val title: TextView = itemView.textView_title
        private val articles: RecyclerView = itemView.recyclerView_articles
        private val adapter = MultiViewAdapter()

        init {
            articles.adapter = adapter
            articles.layoutManager = LinearLayoutManager(itemView.context)
            adapter.registerItemBinders(
                HelpRequestArticleBinder()
            )
        }

        fun bind(request: HelpRequest) {
            adapter.removeAllSections()
            val articleList = ListSection<HelpRequestArticle>()
            articleList.addAll(request.articles!!)
            adapter.addSection(articleList)
        }

    }

    override fun bindViewHolder(holder: ViewModel, item: HelpRequest) {
        holder.bind(item)
    }

    override fun createViewHolder(parent: ViewGroup): ViewModel {
        return ViewModel(inflate(parent, R.layout.view_checkout_request))
    }

    override fun canBindData(item: Any): Boolean {
        return item is HelpRequest
    }
}