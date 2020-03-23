package de.andrestefanov.android.nearbuy.ui.buyer

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
import com.google.android.material.snackbar.Snackbar
import de.andrestefanov.android.nearbuy.R
import de.andrestefanov.android.nearbuy.api.model.RequestEntity
import kotlinx.android.synthetic.main.buyer_overview_fragment.*
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
            getAllRequests().observe(viewLifecycleOwner, Observer { requests ->
                val newRequests = requests.filter {
                    it.status == RequestEntity.StatusEnum.NEW
                }
                val acceptedRequests = requests.filter {
                    it.status == RequestEntity.StatusEnum.ONGOING
                }

                updateNearbyOpenRequests(newRequests)

                updateAcceptedRequests(acceptedRequests)

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    acceptedRequestsSummary.text = Html.fromHtml(
                        "<b>Einkaufen</b> (Insg. " +
                                "${acceptedRequests.map { requestEntity -> requestEntity.articles.size }.sum()} / 20)",
                        Html.FROM_HTML_MODE_LEGACY
                    )
                } else {
                    acceptedRequestsSummary.text = Html.fromHtml(
                        "<b>Einkaufen</b> (Insg. " +
                                "${requests.size} / 20)"
                    )
                }
            })
        }

        acceptedRequestsSummary.setOnClickListener {
            findNavController().navigate(BuyerOverviewFragmentDirections.actionBuyerOverviewFragmentToShoppingListFragment())
        }
    }

    private fun updateAcceptedRequests(acceptedRequests: List<RequestEntity>) {
        acceptedRequestsAdapter.removeAllSections()

        val acceptedRequestsList = ListSection<RequestEntity>()

        acceptedRequestsList.addAll(acceptedRequests)
        acceptedRequestsList.setOnSelectionChangedListener { request: RequestEntity,
                                                             b: Boolean, _: MutableList<RequestEntity> ->
            if (b) {
                val action = BuyerOverviewFragmentDirections.requestDetailAction(request.id.toString())
                findNavController().navigate(action)
            }
        }
        acceptedRequestsAdapter.addSection(acceptedRequestsList)
    }

    private fun updateNearbyOpenRequests(nearRequests: List<RequestEntity>) {
        nearRequestsAdapter.removeAllSections()

        val nearRequestList = ListSection<RequestEntity>()
        nearRequestList.addAll(nearRequests)

        nearRequestList.setOnSelectionChangedListener { helpRequest: RequestEntity,
                                                        b: Boolean, _: MutableList<RequestEntity> ->
            if (b) {
                val action = BuyerOverviewFragmentDirections.requestDetailAction(helpRequest.id.toString())
                findNavController().navigate(action)
            }
        }
        nearRequestsAdapter.addSection(nearRequestList)
    }

}
