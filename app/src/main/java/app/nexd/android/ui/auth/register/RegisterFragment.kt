package app.nexd.android.ui.auth.register

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import app.nexd.android.databinding.FragmentRegisterBinding
import app.nexd.android.ui.auth.register.RegisterFragmentDirections.Companion.toRegisterDetailedFragment
import app.nexd.android.ui.auth.register.RegisterViewModel.Progress.*
import app.nexd.android.ui.common.DefaultSnackbar
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_register.*


class RegisterFragment : Fragment() {

    private val viewModel: RegisterViewModel by viewModels()

    private lateinit var binding: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editText_password_confirm.setOnEditorActionListener { _, i, _ ->
            if (i == EditorInfo.IME_ACTION_DONE) {
                viewModel.register()
            }
            false
        }

        button_register.setOnClickListener {
            viewModel.register()
        }

        viewModel.progress.observe(viewLifecycleOwner, Observer { progress ->
            progressBar.visibility = View.GONE
            editText_first_name.isEnabled = true
            editText_last_name.isEnabled = true
            editText_email.isEnabled = true
            editText_password.isEnabled = true
            editText_password_confirm.isEnabled = true

            when (progress) {
                is Idle -> { /* nothing to do here */
                }
                is Loading -> {
                    progressBar.visibility = View.VISIBLE
                    editText_first_name.isEnabled = false
                    editText_last_name.isEnabled = false
                    editText_email.isEnabled = false
                    editText_password.isEnabled = false
                    editText_password_confirm.isEnabled = false
                }
                is Error -> {
                    DefaultSnackbar(button_register, progress.message, Snackbar.LENGTH_SHORT)
                }
                is Finished -> {
                    findNavController().navigate(
                        toRegisterDetailedFragment()
                    )
                }
            }
        })

        button_dataProtection.setOnClickListener {
            showPrivacyPolicy()
        }
    }

    private fun showPrivacyPolicy() {
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.nexd.app/privacypage")
            )
        )
    }

}
