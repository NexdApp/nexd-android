package de.andrestefanov.android.nearbuy.ui.seeker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import de.andrestefanov.android.nearbuy.R
import de.andrestefanov.android.nearbuy.model.data.HelpRequest
import de.andrestefanov.android.nearbuy.model.data.HelpRequestItem
import kotlinx.android.synthetic.main.seeker_overview_fragment.*
import mva2.adapter.ItemSection
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

        recyclerView.layoutManager = LinearLayoutManager(context)

        adapter = MultiViewAdapter()
        recyclerView.adapter = adapter

        adapter.registerItemBinders(
            HelpRequestBinder(),
            HelpRequestItemBinder()
        )

        viewModel.getHelpRequests().observe(viewLifecycleOwner, Observer { requests ->
            adapter.removeAllSections()

            requests.forEach { request ->
                val headerSection = ItemSection<HelpRequest>(request)
                adapter.addSection(headerSection)

                val itemsSection = ListSection<HelpRequestItem>()
                itemsSection.addAll(request.items)
                adapter.addSection(itemsSection)
            }
        })
    }

}
