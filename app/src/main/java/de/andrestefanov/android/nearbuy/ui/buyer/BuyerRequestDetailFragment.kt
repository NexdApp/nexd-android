package de.andrestefanov.android.nearbuy.ui.buyer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import de.andrestefanov.android.nearbuy.R
import de.andrestefanov.android.nearbuy.model.data.HelpRequestItem
import de.andrestefanov.android.nearbuy.model.network.RestClient
import kotlinx.android.synthetic.main.buyer_request_detail_fragment.*
import mva2.adapter.ListSection
import mva2.adapter.MultiViewAdapter
import mva2.adapter.util.Mode


class BuyerRequestDetailFragment : Fragment() {

    private lateinit var viewModel: BuyerRequestDetailViewModel
    private var itemAdapter = BuyerDetailItemBinder()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val args: BuyerRequestDetailFragmentArgs by navArgs()
        val request = RestClient().getRequest(args.requestId)
            ?: throw IllegalArgumentException("Invalid request uuid given")

        viewModel = ViewModelProvider(viewModelStore,
            BuyerRequestDetailViewModel.BuyerRequestDetailViewModelFactory(request))
            .get(BuyerRequestDetailViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.buyer_request_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val adapter = MultiViewAdapter()
        buyer_request_items.adapter = adapter
        buyer_request_items.layoutManager = LinearLayoutManager(context)

        adapter.registerItemBinders(
            BuyerDetailItemBinder()
        )


        viewModel.request.observe(viewLifecycleOwner, Observer { request ->
            adapter.removeAllSections()

            name.text = request.name

            val list = ListSection<HelpRequestItem>()
            list.addAll(request.items)
            adapter.addSection(list)

            list.setSelectionMode(Mode.SINGLE)
        })

        accept.setOnClickListener {
            Toast.makeText(context, "Anfrage angenommen", Toast.LENGTH_SHORT).show()
        }
    }

}
