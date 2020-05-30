package app.nexd.android.ui.helper.requestOverview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import app.nexd.android.R
import app.nexd.android.api.model.HelpRequest
import app.nexd.android.databinding.FragmentHelperRequestOverviewBinding
import app.nexd.android.ui.common.DefaultSnackbar
import app.nexd.android.ui.common.ExpandableHeaderItemBinder
import app.nexd.android.ui.common.HelpRequestBinder
import app.nexd.android.ui.dialog.SelectTextDialog
import app.nexd.android.ui.helper.requestOverview.HelperOverviewFragmentDirections.Companion.requestDetailAction
import app.nexd.android.ui.helper.requestOverview.HelperOverviewViewModel.Progress.*
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_helper_request_overview.*
import mva2.adapter.HeaderSection
import mva2.adapter.ListSection
import mva2.adapter.MultiViewAdapter
import mva2.adapter.util.Mode
import org.koin.androidx.viewmodel.ext.android.viewModel

class HelperOverviewFragment : Fragment() {

    private val vm: HelperOverviewViewModel by viewModel()
    private lateinit var binding: FragmentHelperRequestOverviewBinding

    private val allRequestsAdapter = MultiViewAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHelperRequestOverviewBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = vm
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView_allRequests.layoutManager = LinearLayoutManager(context)
        recyclerView_allRequests.adapter = allRequestsAdapter
        allRequestsAdapter.registerItemBinders(
            ExpandableHeaderItemBinder(),
            HelpRequestBinder()
        )

        vm.progress.observe(viewLifecycleOwner, Observer { progress ->
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
                            .setOnDismissListener {
                                vm.progress.value = Idle
                            }
                            .setConfirmButton {
                                vm.filterByZipCode(it as String)
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

        vm.myAcceptedRequests.observe(viewLifecycleOwner, Observer { acceptedRequests ->
            vm.openRequests.observe(viewLifecycleOwner, Observer { openRequests ->
                allRequestsAdapter.removeAllSections()
                allRequestsAdapter.setExpansionMode(Mode.MULTIPLE)
                val acceptedRequestsListHeaderSection = HeaderSection(Header("Accepted"))
                val nearRequestListHeaderSection = HeaderSection(Header("Open"))

                val acceptedRequestsList = ListSection<HelpRequest>()
                val nearRequestList = ListSection<HelpRequest>()

                acceptedRequestsListHeaderSection.addSection(acceptedRequestsList)
                nearRequestListHeaderSection.addSection(nearRequestList)

                acceptedRequestsList.addAll(acceptedRequests)
                acceptedRequestsList.setOnSelectionChangedListener { request: HelpRequest,
                                                                     b: Boolean, _ ->
                    if (b) {
                        request.id?.let {
                            findNavController().navigate(requestDetailAction(it))
                        }
                    }
                }
                allRequestsAdapter.addSection(acceptedRequestsListHeaderSection)

                nearRequestList.addAll(openRequests)
                nearRequestList.setOnSelectionChangedListener { helpRequest: HelpRequest,
                                                                b: Boolean, _ ->
                    if (b) {
                        helpRequest.id?.let {
                            findNavController().navigate(requestDetailAction(it))
                        }
                    }
                }
                allRequestsAdapter.addSection(nearRequestListHeaderSection)
            })
        })

        button_shopping.setOnClickListener {
            findNavController().navigate(HelperOverviewFragmentDirections.toShoppingListFragment())
        }
    }

    class Header(val header: String)

}
