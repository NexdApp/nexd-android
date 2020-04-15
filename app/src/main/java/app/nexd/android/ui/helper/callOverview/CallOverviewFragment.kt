package app.nexd.android.ui.helper.callOverview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import app.nexd.android.R
import app.nexd.android.api.model.Call
import app.nexd.android.databinding.FragmentCallOverviewBinding
import app.nexd.android.ui.common.CallBinder
import app.nexd.android.ui.common.DefaultSnackbar
import app.nexd.android.ui.helper.callOverview.CallOverviewFragmentDirections.Companion.toCallTranslateFragment
import app.nexd.android.ui.helper.callOverview.CallOverviewViewModel.Progress.Error
import app.nexd.android.ui.helper.callOverview.CallOverviewViewModel.Progress.Idle
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_call_overview.*
import mva2.adapter.ListSection
import mva2.adapter.MultiViewAdapter

class CallOverviewFragment: Fragment() {

    private val callsAdapter = MultiViewAdapter()

    private val viewModel: CallOverviewViewModel by viewModels()

    private lateinit var binding: FragmentCallOverviewBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCallOverviewBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView_calls.adapter = callsAdapter
        recyclerView_calls.layoutManager = LinearLayoutManager(context)
        callsAdapter.registerItemBinders(
            CallBinder()
        )

        viewModel.getOpenCalls().observe(viewLifecycleOwner, Observer { calls ->
            callsAdapter.removeAllSections()

            val callsList = ListSection<Call>()
            callsList.addAll(calls)
            callsAdapter.addSection(callsList)

            callsList.setOnSelectionChangedListener { call, isSelected, _ ->
                if (isSelected)
                    findNavController().navigate(toCallTranslateFragment(call.sid))
            }
        })

        viewModel.progress.observe(viewLifecycleOwner, Observer { progress ->
            when (progress) {
                is Idle -> {
                }
                is Error -> {
                    DefaultSnackbar(view, progress.message, Snackbar.LENGTH_SHORT)
                }
            }
        })

    }

}