package app.nexd.android.ui.helper.requestOverview

import android.app.AlertDialog
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.RelativeSizeSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import app.nexd.android.R
import app.nexd.android.api.model.HelpRequest
import app.nexd.android.databinding.FragmentHelperRequestOverviewBinding
import app.nexd.android.ui.common.HelpRequestBinder
import app.nexd.android.ui.dialog.SelectTextDialog
import app.nexd.android.ui.helper.requestOverview.HelperOverviewFragmentDirections.Companion.requestDetailAction
import kotlinx.android.synthetic.main.fragment_helper_request_overview.*
import mva2.adapter.ListSection
import mva2.adapter.MultiViewAdapter

class HelperOverviewFragment : Fragment() {

    private val viewModel: HelperOverviewViewModel by viewModels()
    private lateinit var binding: FragmentHelperRequestOverviewBinding
    private lateinit var nearRequestsAdapter: MultiViewAdapter
    private lateinit var acceptedRequestsAdapter: MultiViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHelperRequestOverviewBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
            HelpRequestBinder()
        )

        viewModel.run {
            getMyAcceptedRequests().observe(viewLifecycleOwner, Observer { myAcceptedRequests ->

                updateAcceptedRequests(myAcceptedRequests)

                val acceptedTitle =
                    resources.getString(R.string.helper_request_overview_heading_accepted_section)
                val acceptedInfo = context?.getString(
                    R.string.helper_request_overview_heading_accepted_section_counter,
                    myAcceptedRequests.size
                ) ?: ""
                val accepted = SpannableString(acceptedTitle + acceptedInfo)
                accepted.setSpan(
                    RelativeSizeSpan(0.5f), acceptedTitle.length,
                    acceptedTitle.length + acceptedInfo.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                textView_acceptedLists.text = accepted

                button_shopping.isEnabled = myAcceptedRequests.isNotEmpty()
            })

            getOtherOpenRequests().observe(viewLifecycleOwner, Observer { requests ->
                val nearTitle =
                    context?.getString(R.string.helper_request_overview_heading_available_section)
                        ?: ""
                val nearInfo = getString(R.string.helper_request_overview_heading_open_section_zip)
                val near = SpannableString(nearTitle + nearInfo)
                near.setSpan(
                    RelativeSizeSpan(0.5f), nearTitle.length,
                    nearTitle.length + nearInfo.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                button_nearRequests.text = near

                updateNearbyOpenRequests(requests)
            })
        }

        button_nearRequests.setOnClickListener {
            zipCodeDialog()
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
                        requestDetailAction(
                            id
                        )
                    findNavController().navigate(action)
                }
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
                helpRequest.id?.let {
                    findNavController().navigate(requestDetailAction(it))
                }
            }
        }
        nearRequestsAdapter.addSection(nearRequestList)
    }

    private fun zipCodeDialog() {
        context?.let { context ->
            SelectTextDialog(
                context,
                getString(R.string.helper_request_overview_button_filter_zip),
                viewModel.zipCode ?: ""
            )
                .setConfirmButton {
                    viewModel.filterbyZipCode(it as String)
                }
                .show()
                .window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        }
    }

}
