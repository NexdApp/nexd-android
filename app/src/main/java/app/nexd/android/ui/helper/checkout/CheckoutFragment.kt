package app.nexd.android.ui.helper.checkout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import app.nexd.android.R
import app.nexd.android.ui.view.CheckoutRequestView
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

        viewModel.getShoppingList().observe(viewLifecycleOwner, Observer { shoppingList ->
            container.removeAllViews()

            for (request in shoppingList.helpRequests) {
                val requestView = CheckoutRequestView(context!!, request)
                container.addView(requestView)
            }
        })

        startDelivery.setOnClickListener {
            findNavController().navigate(CheckoutFragmentDirections.actionCheckoutFragmentToDeliveryFragment())
        }
    }
}
