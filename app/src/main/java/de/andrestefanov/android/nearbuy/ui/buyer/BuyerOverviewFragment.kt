package de.andrestefanov.android.nearbuy.ui.buyer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import de.andrestefanov.android.nearbuy.R
import de.andrestefanov.android.nearbuy.model.common.Header
import de.andrestefanov.android.nearbuy.model.data.HelpRequest
import de.andrestefanov.android.nearbuy.model.network.RestClient
import kotlinx.android.synthetic.main.buyer_overview_fragment.*
import mva2.adapter.HeaderSection
import mva2.adapter.ListSection
import mva2.adapter.MultiViewAdapter
import mva2.adapter.util.Mode

class BuyerOverviewFragment : Fragment() {

    private lateinit var viewModel: BuyerOverviewViewModel
    private lateinit var nearRequestsAdapter: MultiViewAdapter
    private lateinit var acceptedRequestsAdapter: MultiViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.buyer_overview_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(BuyerOverviewViewModel::class.java)

        acceptedRequestsAdapter = MultiViewAdapter()
        acceptedRequests.layoutManager = LinearLayoutManager(context)
        acceptedRequests.adapter = acceptedRequestsAdapter

        nearRequestsAdapter = MultiViewAdapter();
        nearRequests.layoutManager = LinearLayoutManager(context)
        nearRequests.adapter = nearRequestsAdapter


        acceptedRequestsAdapter.registerItemBinders(
            BuyerOverviewBinder()
        )
        nearRequestsAdapter.registerItemBinders(
            BuyerOverviewBinder()
        )

        viewModel.run {
            getNearbyOpenRequests().observe(viewLifecycleOwner, Observer { nearRequests ->
                updateNearbyOpenRequests(nearRequests)
            })
            getAcceptedRequests().observe(viewLifecycleOwner, Observer { acceptedRequests ->
                updateAcceptedRequests(acceptedRequests)
                acceptedRequestsSummary.text = "EINKAUFEN (Insg. " +
                        "${viewModel.getAcceptedRequestItems()} / ${RestClient.MAX_ACCEPTING_REQUESTS})"
            })
        }
    }

    private fun updateAcceptedRequests(acceptedRequests: List<HelpRequest>) {
        acceptedRequestsAdapter.removeAllSections()

        val acceptedRequestsList = ListSection<HelpRequest>()

        acceptedRequestsList.addAll(acceptedRequests)
        acceptedRequestsList.setOnSelectionChangedListener { helpRequest: HelpRequest,
                                                             b: Boolean, _: MutableList<HelpRequest> ->
            if (b) {
                val action = BuyerOverviewFragmentDirections.requestDetailAction(helpRequest.id)
                findNavController().navigate(action)
            }
        }
        acceptedRequestsAdapter.addSection(acceptedRequestsList)
    }

    private fun updateNearbyOpenRequests(nearRequests: List<HelpRequest>) {
        nearRequestsAdapter.removeAllSections()

        val nearRequestList = ListSection<HelpRequest>()
        nearRequestList.addAll(nearRequests)

        nearRequestList.setOnSelectionChangedListener { helpRequest: HelpRequest,
                                                        b: Boolean, _: MutableList<HelpRequest> ->
            if (b) {
                val action = BuyerOverviewFragmentDirections.requestDetailAction(helpRequest.id)
                findNavController().navigate(action)
            }
        }
        nearRequestsAdapter.addSection(nearRequestList)
    }

}
