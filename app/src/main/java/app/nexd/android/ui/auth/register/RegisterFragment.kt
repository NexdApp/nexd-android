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
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import app.nexd.android.databinding.FragmentRegisterBinding
import app.nexd.android.ui.auth.register.RegisterFragmentDirections.Companion.toRegisterDetailedFragment
import app.nexd.android.ui.auth.register.RegisterViewModel.Progress.*
import kotlinx.android.synthetic.main.fragment_register.*


class RegisterFragment : Fragment() {

    private lateinit var viewModel: RegisterViewModel

    private lateinit var binding: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)

        binding.viewModel = viewModel

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
            when (progress) {
                is Idle -> { /* nothing to do here */
                }
                is Loading -> { /* TODO (AS): display a loading animation */
                }
                is Error -> { /* TODO (AS): display error toast. */
                }
                is Finished -> {
                    findNavController().navigate(
                        toRegisterDetailedFragment(
                            progress.registrationData.firstName,
                            progress.registrationData.lastName,
                            progress.registrationData.email,
                            progress.registrationData.password
                        )
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
