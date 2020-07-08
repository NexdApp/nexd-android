package app.nexd.android.ui.seeker.overview

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import app.nexd.android.R
import app.nexd.android.api.model.HelpRequest
import app.nexd.android.api.model.HelpRequestArticle
import app.nexd.android.ui.common.helprequest.HelpRequestArticleBinder
import app.nexd.android.ui.seeker.overview.SeekerOverviewHelpRequestBinder.HelpRequestViewHolder
import kotlinx.android.synthetic.main.row_seeker_overview_help_request.view.*
import mva2.adapter.ItemBinder
import mva2.adapter.ItemViewHolder
import mva2.adapter.ListSection
import mva2.adapter.MultiViewAdapter
import java.text.DateFormat

class SeekerOverviewHelpRequestBinder : ItemBinder<HelpRequest, HelpRequestViewHolder>() {

    class HelpRequestViewHolder(itemView: View) : ItemViewHolder<HelpRequest?>(itemView) {
        private val title: TextView = itemView.tv_header
        private val articlesAdapter = MultiViewAdapter()

        init {
            itemView.recyclerView_requests.adapter = articlesAdapter
            itemView.recyclerView_requests.layoutManager = LinearLayoutManager(itemView.context)

            itemView.setOnClickListener {
                toggleItemSelection()
            }

            articlesAdapter.registerItemBinders(
                HelpRequestArticleBinder()
            )
        }

        fun bind(item: HelpRequest) {
            item.createdAt?.let {
                title.text = DateFormat.getDateInstance(DateFormat.FULL).format(it)
            }

            articlesAdapter.removeAllSections()
            val articlesList = ListSection<HelpRequestArticle>()
            articlesList.setOnSelectionChangedListener { _, _, _ ->
                toggleItemSelection()
            }
            articlesList.addAll(item.articles!!)
            articlesAdapter.addSection(articlesList)
        }
    }

    override fun createViewHolder(parent: ViewGroup): HelpRequestViewHolder {
        return HelpRequestViewHolder(inflate(parent, R.layout.row_seeker_overview_help_request))
    }

    override fun bindViewHolder(
        holder: HelpRequestViewHolder,
        item: HelpRequest
    ) {
        holder.bind(item)
    }

    override fun canBindData(item: Any): Boolean {
        return item is HelpRequest
    }
}