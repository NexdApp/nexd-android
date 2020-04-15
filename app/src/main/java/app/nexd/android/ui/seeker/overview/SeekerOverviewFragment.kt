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
import app.nexd.android.api.model.HelpRequest
import kotlinx.android.synthetic.main.fragment_seeker_overview.*
import kotlinx.android.synthetic.main.fragment_seeker_overview.recyclerView_requests
import mva2.adapter.ListSection
import mva2.adapter.MultiViewAdapter

class SeekerOverviewFragment : Fragment() {

    private lateinit var viewModel: SeekerOverviewViewModel

    private lateinit var adapter: MultiViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_seeker_overview, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(SeekerOverviewViewModel::class.java)

        recyclerView_requests.layoutManager = LinearLayoutManager(context)

        adapter = MultiViewAdapter()
        recyclerView_requests.adapter = adapter

        adapter.registerItemBinders(
            SeekerOverviewHelpRequestBinder()
        )

        viewModel.getHelpRequests().observe(viewLifecycleOwner, Observer { requests ->
            adapter.removeAllSections()

            if (requests.isEmpty()) {
                textView_empty.visibility = View.VISIBLE
            } else {
                textView_empty.visibility = View.GONE
                val requestsSection = ListSection<HelpRequest>()
                requestsSection.addAll(requests)
                adapter.addSection(requestsSection)

                requestsSection.setOnSelectionChangedListener { request, isSelected, _ ->
                    if (isSelected) {
                        request.id?.let { requestId ->
                            findNavController().navigate(
                                SeekerOverviewFragmentDirections.toSeekerDetailFragment(requestId)
                            )
                        }
                    }
                }
            }
        })

        button_create_new_help_request.setOnClickListener {
            findNavController().navigate(
                SeekerOverviewFragmentDirections
                    .actionSeekerOverviewFragmentToCreateHelpRequestFragment()
            )
        }
    }

}
