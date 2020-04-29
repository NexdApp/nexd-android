package app.nexd.android.ui.seeker.create

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import app.nexd.android.R
import kotlinx.android.synthetic.main.fragment_seeker_create_request_order_summary.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class SeekerCreateRequestConfirmFragment : Fragment() {
    private val vm: SeekerCreateRequestViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_seeker_create_request_order_summary,
            container,
            false
        )
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        button_confirm.setOnClickListener {
            vm.sendRequest()
        }

        vm.state().observe(viewLifecycleOwner, Observer {
            when (it) {
                SeekerCreateRequestViewModel.State.FINISHED -> {
                    Toast.makeText(
                        requireContext(),
                        "Request successfully submitted",
                        Toast.LENGTH_SHORT
                    ).show()
                    findNavController().navigate(R.id.action_seekerCreateRequestConfirmFragment_to_seekerCreateRequestFragment)
                }
                else -> Log.d(SeekerCreateRequestFragment::class.simpleName, "unhandled state $it")
            }
        })

    }
}