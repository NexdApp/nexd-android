package app.nexd.android.ui.helper.checkout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import app.nexd.android.R
import app.nexd.android.api.model.HelpRequest
import kotlinx.android.synthetic.main.fragment_checkout.*
import mva2.adapter.ListSection
import mva2.adapter.MultiViewAdapter

class CheckoutFragment : Fragment() {

    private val viewModel: CheckoutViewModel by viewModels()

    private val adapter = MultiViewAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_checkout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView_helpRequests.adapter = adapter
        recyclerView_helpRequests.layoutManager = LinearLayoutManager(context)
        adapter.registerItemBinders(
            CheckoutHelpRequestBinder()
        )

        viewModel.getShoppingList().observe(viewLifecycleOwner, Observer { shoppingList ->
            adapter.removeAllSections()

            val requestList = ListSection<HelpRequest>()
            requestList.addAll(shoppingList.helpRequests)
            adapter.addSection(requestList)
        })

        button_deliver.setOnClickListener {
            findNavController().navigate(CheckoutFragmentDirections.actionCheckoutFragmentToDeliveryFragment())
        }
    }
}
