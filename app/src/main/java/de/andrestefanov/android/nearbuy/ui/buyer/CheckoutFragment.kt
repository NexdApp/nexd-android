package de.andrestefanov.android.nearbuy.ui.buyer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import de.andrestefanov.android.nearbuy.R
import de.andrestefanov.android.nearbuy.ui.view.CheckoutRequestView
import kotlinx.android.synthetic.main.fragment_checkout.*

class CheckoutFragment : Fragment() {

    private lateinit var viewModel: CheckoutViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_checkout, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this).get(CheckoutViewModel::class.java)

        viewModel.getAcceptedRequests().observe(viewLifecycleOwner, Observer { requests ->
            container.removeAllViews()

            for (request in requests) {
                val requestView = CheckoutRequestView(context!!, request)
                container.addView(requestView)
            }
        })

        startDelivery.setOnClickListener {
            findNavController().navigate(R.id.action_checkoutFragment_to_deliveryFragment)
        }
    }
}
