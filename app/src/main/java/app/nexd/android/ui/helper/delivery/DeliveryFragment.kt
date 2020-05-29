package app.nexd.android.ui.helper.delivery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import app.nexd.android.R
import app.nexd.android.api.model.HelpRequest
import app.nexd.android.ui.dialog.SelectBasicDialog
import kotlinx.android.synthetic.main.fragment_delivery.*
import mva2.adapter.ListSection
import mva2.adapter.MultiViewAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class DeliveryFragment : Fragment() {

    private val vm: DeliveryViewModel by viewModel()

    private val adapter = MultiViewAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_delivery, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        progressBar.visibility = View.VISIBLE

        recyclerView_helpRequests.adapter = adapter
        recyclerView_helpRequests.layoutManager = LinearLayoutManager(context)

        adapter.registerItemBinders(
            DeliveryHelpRequestBinder()
        )

        vm.getShoppingList().observe(viewLifecycleOwner, Observer { shoppingList ->
            progressBar.visibility = View.VISIBLE
            adapter.removeAllSections()

            val requestList = ListSection<HelpRequest>()
            requestList.addAll(shoppingList.helpRequests)
            adapter.addSection(requestList)
            progressBar.visibility = View.GONE

            context?.let { context ->
                closeRequest.setOnClickListener {
                    SelectBasicDialog(
                        context, getString(R.string.delivery_dialog_deliver_title),
                        getString(R.string.delivery_dialog_deliver_description)
                    )
                        .setConfirmButton(getString(R.string.delivery_dialog_delivery_button_confirm)) {
                            vm.completeShoppingList(shoppingList.id)
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
