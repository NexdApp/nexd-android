package app.nexd.android.ui.seeker.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import app.nexd.android.R
import app.nexd.android.api.model.RequestArticle
import app.nexd.android.api.model.RequestEntity
import kotlinx.android.synthetic.main.seeker_overview_fragment.*
import mva2.adapter.ListSection
import mva2.adapter.MultiViewAdapter

class SeekerOverviewFragment : Fragment() {

    private lateinit var viewModel: SeekerOverviewViewModel

    private lateinit var adapter: MultiViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.seeker_overview_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SeekerOverviewViewModel::class.java)

        recyclerView_articles.layoutManager = LinearLayoutManager(context)

        adapter = MultiViewAdapter()
        recyclerView_articles.adapter = adapter

        viewModel.getArticles().observe(viewLifecycleOwner, Observer { articles ->
            adapter.registerItemBinders(
                HelpRequestBinder(articles)
            )

            viewModel.getHelpRequests().observe(viewLifecycleOwner, Observer { requests ->
                adapter.removeAllSections()

                val requestsSection = ListSection<RequestEntity>()
                requestsSection.addAll(requests)
                adapter.addSection(requestsSection)
            })
        })

        button_create_new_help_request.setOnClickListener {
            findNavController().navigate(R.id.action_seekerOverviewFragment_to_createHelpRequestFragment)
        }
    }

}
