package app.nexd.android.ui.auth

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import app.nexd.android.Preferences
import app.nexd.android.R
import app.nexd.android.api
import app.nexd.android.api.model.LoginPayload
import app.nexd.android.ui.MainActivity
import com.google.android.material.snackbar.Snackbar
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.auth_fragment.button_login
import kotlinx.android.synthetic.main.buyer_request_detail_fragment.*
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment() {

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        edittext_password.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                login()
            }
            false
        }

        button_login.setOnClickListener {
            login()
        }
    }

    private fun login() {
        (activity as MainActivity).hideKeyboard()
        val email = edittext_email.text.trim().toString()
        val password = edittext_password.text.trim().toString()

        var correct = true
        if (email.isBlank()) {
            edittext_email.error = "Ausfüllen"
            correct = false
        }
        if (password.isBlank()) {
            edittext_password.error = "Ausfüllen"
            correct = false
        }

        if (correct) {
            viewModel.login(email, password)
                .observe(viewLifecycleOwner, Observer { viewState ->
                    if (viewState.successful) {
                        findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRoleFragment())
                    } else if (!viewState.errorResponse.isNullOrBlank()) {
                        Snackbar.make(edittext_email, viewState.errorResponse, Snackbar.LENGTH_SHORT).show()
                    }
                })
        }
    }
}
