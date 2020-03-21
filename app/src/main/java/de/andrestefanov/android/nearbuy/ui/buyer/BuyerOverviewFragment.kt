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
import de.andrestefanov.android.nearbuy.model.data.HelpRequest
import de.andrestefanov.android.nearbuy.ui.seeker.HelpRequestBinder
import kotlinx.android.synthetic.main.buyer_overview_fragment.*
import mva2.adapter.ItemSection
import mva2.adapter.ListSection
import mva2.adapter.MultiViewAdapter
import mva2.adapter.util.Mode

class BuyerOverviewFragment : Fragment() {

    private lateinit var viewModel: BuyerOverviewViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.buyer_overview_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(BuyerOverviewViewModel::class.java)

        val adapter = MultiViewAdapter();
        nearRequests.layoutManager = LinearLayoutManager(context)
        nearRequests.adapter = adapter

        adapter.registerItemBinders(
            BuyerOverviewBinder()
        )

        viewModel.getNearRequests().observe(viewLifecycleOwner, Observer { nearRequests ->
            adapter.removeAllSections()

            val list = ListSection<HelpRequest>()
            list.addAll(nearRequests)

            adapter.addSection(list)

            list.setSelectionMode(Mode.SINGLE)
            list.setOnSelectionChangedListener { helpRequest: HelpRequest, b: Boolean, _: MutableList<HelpRequest> ->
                if (b) {
                    val action = BuyerOverviewFragmentDirections.requestDetailAction(helpRequest.id)
                    findNavController().navigate(action)
                }
            }
        })
    }

}
