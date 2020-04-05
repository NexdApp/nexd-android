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
import app.nexd.android.ui.helper.callOverview.CallOverviewFragmentDirections.Companion.toCallTranslateFragment
import kotlinx.android.synthetic.main.fragment_call_overview.*
import mva2.adapter.ListSection
import mva2.adapter.MultiViewAdapter

class CallOverviewFragment: Fragment() {

    private val callsAdapter = MultiViewAdapter()

    private val viewModel: CallOverviewViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_call_overview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView_calls.adapter = callsAdapter
        recyclerView_calls.layoutManager = LinearLayoutManager(context)
        callsAdapter.registerItemBinders(
            CallItemBinder()
        )

        viewModel.getOpenCalls().observe(viewLifecycleOwner, Observer { calls ->
            callsAdapter.removeAllSections()

            val callsList = ListSection<Call>()
            callsList.addAll(calls)
            callsAdapter.addSection(callsList)

            callsList.setOnSelectionChangedListener { call, isSelected, _ ->
                if (isSelected)
                    findNavController().navigate(toCallTranslateFragment(call.id))
            }
        })

    }

}