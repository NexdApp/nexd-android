package app.nexd.android.ui.seeker.overview

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import app.nexd.android.R
import app.nexd.android.api.model.Article
import app.nexd.android.api.model.RequestArticle
import app.nexd.android.api.model.RequestEntity
import app.nexd.android.ui.seeker.overview.HelpRequestBinder.HelpRequestViewHolder
import kotlinx.android.synthetic.main.request_header_view.view.*
import mva2.adapter.ItemBinder
import mva2.adapter.ItemViewHolder
import mva2.adapter.ListSection
import mva2.adapter.MultiViewAdapter
import java.text.DateFormat

class HelpRequestBinder(private val articles: List<Article>) : ItemBinder<RequestEntity, HelpRequestViewHolder>() {

    class HelpRequestViewHolder(itemView: View, articles: List<Article>) : ItemViewHolder<RequestEntity?>(itemView) {
        private val title: TextView = itemView.tv_header
        private val articlesAdapter = MultiViewAdapter()

        init {
            itemView.recyclerView_articles.adapter = articlesAdapter
            itemView.recyclerView_articles.layoutManager = LinearLayoutManager(itemView.context)

            itemView.setOnClickListener {
                toggleItemSelection()
            }

            articlesAdapter.registerItemBinders(
                HelpRequestItemBinder(articles)
            )
        }

        fun bind(item: RequestEntity) {
            title.text = DateFormat.getDateInstance(DateFormat.FULL).format(item.createdAt)
            articlesAdapter.removeAllSections()
            val articlesList = ListSection<RequestArticle>()
            articlesList.setOnSelectionChangedListener { _, _, _ ->
                toggleItemSelection()
            }
            articlesList.addAll(item.articles)
            articlesAdapter.addSection(articlesList)
        }
    }

    override fun createViewHolder(parent: ViewGroup): HelpRequestViewHolder {
        return HelpRequestViewHolder(inflate(parent, R.layout.request_header_view), articles)
    }

    override fun bindViewHolder(
        holder: HelpRequestViewHolder,
        item: RequestEntity
    ) {
        holder.bind(item)
    }

    override fun canBindData(item: Any): Boolean {
        return item is RequestEntity
    }
}