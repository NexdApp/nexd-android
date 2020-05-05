package app.nexd.android.ui.seeker.create.address

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import app.nexd.android.R
import app.nexd.android.databinding.FragmentSeekerCreateRequestConfirmAddressBinding
import app.nexd.android.di.sharedGraphViewModel
import app.nexd.android.ui.common.DefaultSnackbar
import app.nexd.android.ui.seeker.create.SeekerCreateRequestViewModel
import app.nexd.android.ui.seeker.create.SeekerCreateRequestViewModel.Progress.*
import app.nexd.android.ui.seeker.create.articles.SeekerCreateRequestEnterArticlesFragment
import com.google.android.material.snackbar.Snackbar

class SeekerCreateRequestConfirmAddressFragment : Fragment() {
    private val vm: SeekerCreateRequestViewModel by sharedGraphViewModel(R.id.nav_seeker_create_request)

    private lateinit var binding: FragmentSeekerCreateRequestConfirmAddressBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            FragmentSeekerCreateRequestConfirmAddressBinding.inflate(inflater, container, false)
        binding.viewModel = vm
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initUi()
        setVmObserver()
    }

    private fun initUi() {
        binding.editTextPhoneNumber.setOnEditorActionListener { _, i, _ ->
            if (i == EditorInfo.IME_ACTION_DONE) {
                vm.sendRequest()
            }
            false
        }

        binding.buttonConfirm.setOnClickListener {
            vm.sendRequest()
        }
    }

    private fun setVmObserver() {
        vm.progress.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Idle -> {
                    // nothing to do
                }
                is Loading -> {
                    //
                }
                is Error -> {
                    it.message?.let { errorMessageId ->
                        DefaultSnackbar(binding.root, errorMessageId, Snackbar.LENGTH_SHORT)
                    }
                }
                is Finished -> {
                    findNavController().navigate(SeekerCreateRequestConfirmAddressFragmentDirections.toSeekerOverviewFragment())
                }
                else -> Log.d(
                    SeekerCreateRequestEnterArticlesFragment::class.simpleName,
                    "unhandled state $it"
                )
            }
        })
    }
}