package app.nexd.android.ui.helper.delivery

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
import app.nexd.android.databinding.FragmentDeliveryBinding
import app.nexd.android.ui.dialog.SelectBasicDialog
import kotlinx.android.synthetic.main.fragment_delivery.*
import mva2.adapter.ListSection
import mva2.adapter.MultiViewAdapter

class DeliveryFragment : Fragment() {

    private val viewModel: DeliveryViewModel by viewModels()

    private val adapter = MultiViewAdapter()

    private lateinit var binding: FragmentDeliveryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDeliveryBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView_helpRequests.adapter = adapter
        recyclerView_helpRequests.layoutManager = LinearLayoutManager(context)

        adapter.registerItemBinders(
            DeliveryHelpRequestBinder()
        )

        viewModel.getShoppingList().observe(viewLifecycleOwner, Observer { shoppingList ->
            adapter.removeAllSections()

            val requestList = ListSection<HelpRequest>()
            requestList.addAll(shoppingList.helpRequests)
            adapter.addSection(requestList)

            context?.let { context ->
                closeRequest.setOnClickListener {
                    SelectBasicDialog(
                        context, getString(R.string.delivery_dialog_deliver_title),
                        getString(R.string.delivery_dialog_deliver_description)
                    )
                        .setConfirmButton(getString(R.string.delivery_dialog_delivery_button_confirm)) {
                            viewModel.completeShoppingList(shoppingList.id)
                                .observe(viewLifecycleOwner, Observer {
                                    findNavController().navigate(DeliveryFragmentDirections.toRoleFragment())
                                })
                        }
                        .show()
                }
            }
        })
    }

}
