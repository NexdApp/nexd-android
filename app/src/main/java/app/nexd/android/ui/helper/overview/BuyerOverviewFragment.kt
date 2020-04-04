package app.nexd.android.ui.helper.overview

import android.os.Build
import android.os.Bundle
import android.text.Html
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
import kotlinx.android.synthetic.main.fragment_helper_request_overview.*
import mva2.adapter.ListSection
import mva2.adapter.MultiViewAdapter

class BuyerOverviewFragment : Fragment() {

    private lateinit var viewModel: BuyerOverviewViewModel
    private lateinit var nearRequestsAdapter: MultiViewAdapter
    private lateinit var acceptedRequestsAdapter: MultiViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_helper_request_overview, container, false)
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
            HelpRequestBinder()
        )
        nearRequestsAdapter.registerItemBinders(
            HelpRequestBinder()
        )

        viewModel.run {
            getMyAcceptedRequests().observe(viewLifecycleOwner, Observer { requests ->

                updateAcceptedRequests(requests)

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    acceptedRequestsSummary.text = Html.fromHtml(
                        "<b>" +
                                getString(R.string.helper_request_overview_button_summary_title)
                                + "</b> (" +
                            getString(R.string.helper_request_overview_button_summary_details)
                                + " " +
                                requests.sumBy { it.articles?.size ?: 0 }
                                + "/ 20)",
                        Html.FROM_HTML_MODE_LEGACY
                    )
                } else {
                    acceptedRequestsSummary.text = Html.fromHtml(
                        "<b>%1</b> (%2 %3 / 20)".format(getString(R.string.helper_request_overview_button_summary_title),
                            getString(R.string.helper_request_overview_button_summary_details),
                            requests.map { it.articles?.size ?: 0 }.sum())
                    )
                }
            })

            getOtherOpenRequests().observe(viewLifecycleOwner, Observer { requests ->
                updateNearbyOpenRequests(requests)
            })
        }

        acceptedRequestsSummary.setOnClickListener {
            findNavController().navigate(BuyerOverviewFragmentDirections.actionBuyerOverviewFragmentToShoppingListFragment())
        }
    }

    override fun onResume() {
        super.onResume()

        viewModel.reloadData()
    }

    private fun updateAcceptedRequests(acceptedRequests: List<HelpRequest>) {
        acceptedRequestsAdapter.removeAllSections()

        val acceptedRequestsList = ListSection<HelpRequest>()

        acceptedRequestsList.addAll(acceptedRequests)
        acceptedRequestsList.setOnSelectionChangedListener { request: HelpRequest,
                                                             b: Boolean, _: MutableList<HelpRequest> ->
            if (b) {
                val action =
                    BuyerOverviewFragmentDirections.requestDetailAction(
                        request.id.toString()
                    )
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
                val action =
                    BuyerOverviewFragmentDirections.requestDetailAction(
                        helpRequest.id.toString()
                    )
                findNavController().navigate(action)
            }
        }
        nearRequestsAdapter.addSection(nearRequestList)
    }

}
