package app.nexd.android.ui.auth

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
import app.nexd.android.R
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_register.*
import kotlinx.android.synthetic.main.fragment_register.button_register
import java.util.*


class RegisterFragment : Fragment() {

    private val viewModel: RegisterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editText_password_confirm.setOnEditorActionListener { _, i, _ ->
            if (i == EditorInfo.IME_ACTION_DONE)
                register()
            false
        }

        button_register.setOnClickListener {
            register()
        }

        button_dataProtection.setOnClickListener {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://www.nexd.app/privacypage")
                )
            )
        }
    }

    private fun register() {
        val firstName = editText_first_name.text.toString().trim()
        val lastName = editText_surname.text.toString().trim()
        val email = editText_email.text.toString().trim().toLowerCase(Locale.getDefault())
        val password = editText_password.text.toString().trim()
        val passwordConfirm = editText_password_confirm.text.toString().trim()

        var successful = true
        if (password != passwordConfirm) {
            successful = false
            editText_password_confirm.error = getString(R.string.passwords_not_the_same)
        }

        if (firstName.isEmpty()) {
            successful = false
            editText_first_name.error = getString(R.string.fillUp)
        }

        if (lastName.isEmpty()) {
            successful = false
            editText_surname.error = getString(R.string.fillUp)
        }

        if (email.isEmpty()) {
            successful = false
            editText_email.error = getString(R.string.fillUp)
        }

        if (password.isEmpty() || password.length < 6) {
            successful = false
            editText_password.error = getString(R.string.password_too_short)
        }

        if (successful) {

            viewModel.register(
                firstName,
                lastName,
                email,
                password
            ).observe(viewLifecycleOwner, Observer { request ->
                if (request.successful) {
                    findNavController().navigate(RegisterFragmentDirections.toRoleFragment())
                } else if (!request.error.isNullOrBlank()) {
                    Snackbar.make(editText_email, request.error, Snackbar.LENGTH_SHORT).show()
                }
            })
        }
    }

}
