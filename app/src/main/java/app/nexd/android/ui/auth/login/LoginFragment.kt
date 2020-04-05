package app.nexd.android.ui.auth.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import app.nexd.android.databinding.FragmentLoginBinding
import app.nexd.android.ui.auth.login.LoginFragmentDirections.Companion.toRoleFragment
import app.nexd.android.ui.auth.login.LoginViewModel.Progress.*
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment() {

    private val viewModel: LoginViewModel by viewModels()

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

        binding.viewModel = viewModel

        editText_password.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                viewModel.login()
            }
            false
        }

        viewModel.progress.observe(viewLifecycleOwner, Observer { progress ->
            when(progress) {
                is Idle -> { /* nothing to do here */ }
                is Loading -> { /* TODO: show loading animation */ }
                is Error -> {
                    Toast.makeText(context, progress.message, Toast.LENGTH_SHORT).show()
                }
                is Finished -> proceed()
            }
        })
    }

    private fun proceed() {
        findNavController().navigate(toRoleFragment())
    }
}