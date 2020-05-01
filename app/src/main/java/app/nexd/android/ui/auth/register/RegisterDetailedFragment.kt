package app.nexd.android.ui.auth.register

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import app.nexd.android.R
import app.nexd.android.databinding.FragmentRegisterDetailedBinding
import app.nexd.android.ui.auth.register.RegisterDetailedViewModel.Progress.*
import app.nexd.android.ui.common.Constants
import app.nexd.android.ui.common.DefaultSnackbar
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_register_detailed.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterDetailedFragment : Fragment() {

    private val vm: RegisterDetailedViewModel by viewModel()

    private lateinit var binding: FragmentRegisterDetailedBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterDetailedBinding.inflate(inflater, container, false)
        binding.viewModel = vm
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(findNavController()) {
            val appBarConfiguration = AppBarConfiguration(setOf(R.id.registerDetailedFragment))
            register_detailed_toolbar.setupWithNavController(this, appBarConfiguration)
        }

        editText_city.setOnEditorActionListener { _, i, _ ->
            if (i == EditorInfo.IME_ACTION_DONE) {
                vm.setUserDetails()
            }
            false
        }

        vm.progress.observe(viewLifecycleOwner, Observer { progress ->
            progressBar.visibility = View.GONE
            editText_phoneNumber.isEnabled = true
            editText_street.isEnabled = true
            editText_houseNr.isEnabled = true
            editText_zipCode.isEnabled = true
            editText_city.isEnabled = true

            when (progress) {
                is Idle -> { /* do nothing in idle */ }
                is Loading -> {
                    progressBar.visibility = View.VISIBLE
                    editText_phoneNumber.isEnabled = false
                    editText_street.isEnabled = false
                    editText_houseNr.isEnabled = false
                    editText_zipCode.isEnabled = false
                    editText_city.isEnabled = false
                }
                is Error -> {
                    progress.message?.let {
                        DefaultSnackbar(view, it, Snackbar.LENGTH_SHORT)
                    }
                }
                is Finished -> {
                    findNavController().navigate(RegisterDetailedFragmentDirections.toRoleFragment())
                }
            }
        })

        button_dataProtection_detail_registration.setOnClickListener {
            showPrivacyPolicy()
        }
    }

    private fun showPrivacyPolicy() {
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse(Constants.PRIVACY_POLICY_URL)
            )
        )
    }
}