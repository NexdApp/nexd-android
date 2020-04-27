package app.nexd.android.ui.auth.login

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
import androidx.navigation.ui.setupWithNavController
import app.nexd.android.databinding.FragmentLoginBinding
import app.nexd.android.ui.auth.login.LoginFragmentDirections.Companion.toRoleFragment
import app.nexd.android.ui.auth.login.LoginViewModel.Progress.*
import app.nexd.android.ui.common.Constants
import app.nexd.android.ui.common.DefaultSnackbar
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

    private val vm: LoginViewModel by viewModel()

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        login_toolbar.setupWithNavController(findNavController())

        binding.viewModel = vm

        editText_password.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                vm.login()
            }
            false
        }

        vm.progress.observe(viewLifecycleOwner, Observer { progress ->
            progressBar.visibility = View.GONE
            button_login.isEnabled = true
            editText_email.isEnabled = true
            editText_password.isEnabled = true

            when(progress) {
                is Idle -> {
                    /* nothing to do here */
                }
                is Loading -> {
                    progressBar.visibility = View.VISIBLE
                    button_login.isEnabled = false
                    editText_email.isEnabled = false
                    editText_password.isEnabled = false
                }
                is Error -> {
                    DefaultSnackbar(button_login, progress.message, Snackbar.LENGTH_SHORT)
                }
                is Finished -> proceed()
            }
        })

        button_dataProtection.setOnClickListener {
            showPrivacyPolicy()
        }
    }

    private fun proceed() {
        findNavController().navigate(toRoleFragment())
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