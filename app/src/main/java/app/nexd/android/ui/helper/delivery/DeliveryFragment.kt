package app.nexd.android.ui.helper.delivery

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import app.nexd.android.R
import app.nexd.android.ui.view.RequestDeliveryView
import app.nexd.android.ui.view.SelectDialog
import kotlinx.android.synthetic.main.fragment_delivery.*

class DeliveryFragment : Fragment() {

    private lateinit var viewModel: DeliveryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_delivery, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = ViewModelProvider(this).get(DeliveryViewModel::class.java)

        viewModel.getAcceptedRequests().observe(viewLifecycleOwner, Observer { requests ->
            for (request in requests) {
                val deliveryView = RequestDeliveryView(context!!, request)
                container.addView(deliveryView)
            }
        })

        closeRequest.setOnClickListener {
            SelectDialog(activity!!, getString(R.string.delivery_dialog_deliver_title),
                getString(R.string.delivery_dialog_deliver_description))
                .setConfirmButton(getString(R.string.delivery_dialog_delivery_button_confirm)) {
                    findNavController().navigate(DeliveryFragmentDirections.actionDeliveryFragmentToRoleFragment())
                }.show()
        }
    }

}
