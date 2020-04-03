package app.nexd.android.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import app.nexd.android.R
import app.nexd.android.ui.MainActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_auth.button_login
import kotlinx.android.synthetic.main.fragment_login.*
import retrofit2.HttpException

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

        editText_password.setOnEditorActionListener { _, actionId, _ ->
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
        val email = editText_email.text.trim().toString()
        val password = editText_password.text.trim().toString()

        var correct = true
        if (email.isBlank()) {
            editText_email.error = getString(R.string.error_message_login_field_missing)
            correct = false
        }
        if (password.isBlank()) {
            editText_password.error = getString(R.string.error_message_login_field_missing)
            correct = false
        }

        if (correct) {
            viewModel.login(email, password)
                .observe(viewLifecycleOwner, Observer { viewState ->
                    if (viewState.successful) {
                        findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRoleFragment())

                    } else if (viewState.errorResponse != null) {
                        if (viewState.errorResponse is HttpException) {
                            Snackbar.make(editText_email, when (viewState.errorResponse.code()) {
                                401 -> context!!.getString(R.string.error_message_login_failed)
                                else -> viewState.errorResponse.message()
                            }, Snackbar.LENGTH_SHORT).show()
                        }
                    }
                })
        }
    }
}
