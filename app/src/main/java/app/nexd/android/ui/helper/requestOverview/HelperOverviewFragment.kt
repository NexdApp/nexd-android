package app.nexd.android.ui.helper.requestOverview

import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.RelativeSizeSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import app.nexd.android.R
import app.nexd.android.api.model.HelpRequest
import app.nexd.android.databinding.FragmentHelperRequestOverviewBinding
import app.nexd.android.ui.common.DefaultSnackbar
import app.nexd.android.ui.common.HelpRequestBinder
import app.nexd.android.ui.dialog.SelectTextDialog
import app.nexd.android.ui.helper.requestOverview.HelperOverviewFragmentDirections.Companion.requestDetailAction
import app.nexd.android.ui.helper.requestOverview.HelperOverviewViewModel.Progress.*
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_helper_request_overview.*
import mva2.adapter.ListSection
import mva2.adapter.MultiViewAdapter

class HelperOverviewFragment : Fragment() {

    private val viewModel: HelperOverviewViewModel by viewModels()
    private lateinit var binding: FragmentHelperRequestOverviewBinding

    private val nearRequestsAdapter = MultiViewAdapter()
    private val acceptedRequestsAdapter = MultiViewAdapter()

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

        recyclerView_acceptedRequests.layoutManager = LinearLayoutManager(context)
        recyclerView_acceptedRequests.adapter = acceptedRequestsAdapter
        acceptedRequestsAdapter.registerItemBinders(
            HelpRequestBinder()
        )

        recyclerView_nearRequests.layoutManager = LinearLayoutManager(context)
        recyclerView_nearRequests.adapter = nearRequestsAdapter
        nearRequestsAdapter.registerItemBinders(
            HelpRequestBinder()
        )

        viewModel.run {
            progress.observe(viewLifecycleOwner, Observer { progress ->
                when (progress) {
                    is Idle -> {
                        progressBar.visibility = View.GONE
                    }
                    is Loading -> {
                        progressBar.visibility = View.VISIBLE
                    }
                    is ZipCodeDialog -> {
                        context?.let { context ->
                            SelectTextDialog(
                                context,
                                getString(R.string.helper_request_overview_button_filter_zip),
                                progress.zipCode
                            )
                                .setConfirmButton {
                                    viewModel.filterbyZipCode(it as String)
                                }
                                .show()
                                .window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
                        }
                    }
                    is Error -> {
                        progressBar.visibility = View.GONE
                        DefaultSnackbar(view, progress.message, Snackbar.LENGTH_SHORT)
                    }
                }
            })

            myAcceptedRequests.observe(viewLifecycleOwner, Observer { acceptedRequests ->
                acceptedRequestsAdapter.removeAllSections()

                val acceptedRequestsList = ListSection<HelpRequest>()

                acceptedRequestsList.addAll(acceptedRequests)
                acceptedRequestsList.setOnSelectionChangedListener { request: HelpRequest,
                                                                     b: Boolean, _ ->
                    if (b) {
                        request.id?.let {
                            findNavController().navigate(requestDetailAction(it))
                        }
                    }
                }
                acceptedRequestsAdapter.addSection(acceptedRequestsList)
            })

            openRequests.observe(viewLifecycleOwner, Observer { requests ->
                nearRequestsAdapter.removeAllSections()

                val nearRequestList = ListSection<HelpRequest>()
                nearRequestList.addAll(requests)
                nearRequestList.setOnSelectionChangedListener { helpRequest: HelpRequest,
                                                                b: Boolean, _ ->
                    if (b) {
                        helpRequest.id?.let {
                            findNavController().navigate(requestDetailAction(it))
                        }
                    }
                }
                nearRequestsAdapter.addSection(nearRequestList)
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
        viewModel.loadMyAcceptedRequests()
        viewModel.loadNearOpenRequests()
    }

}
