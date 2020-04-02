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
import kotlinx.android.synthetic.main.fragment_register.button_data_protection
import kotlinx.android.synthetic.main.fragment_register.button_register
import kotlinx.android.synthetic.main.fragment_register_detailed.*
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

        edittext_password_confirm.setOnEditorActionListener { _, i, _ ->
            if (i == EditorInfo.IME_ACTION_DONE)
                register()
            false
        }

        button_register.setOnClickListener {
            register()
        }

        button_data_protection.setOnClickListener {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://www.nexd.app/privacypage")
                )
            )
        }
    }

    private fun register() {
        val firstname = edittext_first_name.text.toString().trim()
        val lastname = edittext_surname.text.toString().trim()
        val email = edittext_email.text.toString().trim().toLowerCase(Locale.getDefault())
        val password = edittext_password.text.toString().trim()
        val passwordConfirm = edittext_password_confirm.text.toString().trim()

        var successful = true
        if (password != passwordConfirm) {
            successful = false
            edittext_password_confirm.error = "Passwörter stimmen nicht überein"
        }

        if (firstname.isEmpty()) {
            successful = false
            edittext_first_name.error = "Bitte ausfüllen"
        }

        if (lastname.isEmpty()) {
            successful = false
            edittext_surname.error = "Bitte ausfüllen"
        }

        if (email.isEmpty()) {
            successful = false
            edittext_email.error = "ungültig"
        }

        if (password.isEmpty() || password.length < 6) {
            successful = false
            edittext_password.error = "mind. 6 Zeichen enthalten"
        }

        if (successful) {

            viewModel.register(
                firstname,
                lastname,
                email,
                password
            ).observe(viewLifecycleOwner, Observer { request ->
                if (request.successful) {
                    findNavController().navigate(RegisterFragmentDirections.toRoleFragment())
                } else if (!request.error.isNullOrBlank()) {
                    Snackbar.make(edittext_phonenumber, request.error, Snackbar.LENGTH_SHORT).show()
                }
            })
        }
    }

}
