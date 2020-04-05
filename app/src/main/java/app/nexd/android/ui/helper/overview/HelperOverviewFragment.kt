package app.nexd.android.ui.helper.overview

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.text.SpannableString
import android.text.Spanned
import android.text.style.RelativeSizeSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import app.nexd.android.R
import app.nexd.android.api.model.HelpRequest
import kotlinx.android.synthetic.main.fragment_helper_request_overview.*
import mva2.adapter.ListSection
import mva2.adapter.MultiViewAdapter

class HelperOverviewFragment : Fragment() {

    private lateinit var viewModel: HelperOverviewViewModel
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
        viewModel = ViewModelProvider(this).get(HelperOverviewViewModel::class.java)

        acceptedRequestsAdapter = MultiViewAdapter()
        recyclerView_acceptedRequests.layoutManager = LinearLayoutManager(context)
        recyclerView_acceptedRequests.adapter = acceptedRequestsAdapter

        nearRequestsAdapter = MultiViewAdapter()
        recyclerView_nearRequests.layoutManager = LinearLayoutManager(context)
        recyclerView_nearRequests.adapter = nearRequestsAdapter


        acceptedRequestsAdapter.registerItemBinders(
            HelpRequestBinder()
        )
        nearRequestsAdapter.registerItemBinders(
            AvailableRequestBinder()
        )

        viewModel.run {
            getMyAcceptedRequests().observe(viewLifecycleOwner, Observer { myAcceptedRequests ->

                updateAcceptedRequests(myAcceptedRequests)

                val title = "Accepted lists "
                val small = "(${myAcceptedRequests.size} / 20)"
                val acceptedTitle = SpannableString(title + small)
                acceptedTitle.setSpan(
                    RelativeSizeSpan(0.5f), title.length,
                    title.length + small.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                textView_acceptedLists.text = acceptedTitle

                button_shopping.isEnabled = myAcceptedRequests.isNotEmpty()
            })

            getOtherOpenRequests().observe(viewLifecycleOwner, Observer { requests ->
                updateNearbyOpenRequests(requests)
            })
        }

        button_shopping.setOnClickListener {
            findNavController().navigate(HelperOverviewFragmentDirections.toShoppingListFragment())
        }

        button_previousRequests.setOnClickListener {
            findNavController().navigate(HelperOverviewFragmentDirections.toFinishedFragment())
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
                request.id?.let { id ->
                    val action =
                        HelperOverviewFragmentDirections.requestDetailAction(
                            id
                        )
                    findNavController().navigate(action)
                }
            }
        }
        acceptedRequestsAdapter.addSection(acceptedRequestsList)
    }

    private fun updateNearbyOpenRequests(nearRequests: List<HelperOverviewViewModel.AvailableRequestWrapper>) {
        nearRequestsAdapter.removeAllSections()

        val nearRequestList = ListSection<HelperOverviewViewModel.AvailableRequestWrapper>()
        nearRequestList.addAll(nearRequests)

        nearRequestList.setOnSelectionChangedListener { helpRequest: HelperOverviewViewModel.AvailableRequestWrapper,
                                                        b: Boolean, _: MutableList<HelperOverviewViewModel.AvailableRequestWrapper> ->
            if (b) {
                val action = HelperOverviewFragmentDirections.requestDetailAction(
                    helpRequest.id
                )
                findNavController().navigate(action)
            }
        }
        nearRequestsAdapter.addSection(nearRequestList)
    }

}
