package app.nexd.android.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import app.nexd.android.Preferences
import app.nexd.android.R
import app.nexd.android.api
import app.nexd.android.api.model.RegisterPayload
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_register.*
import java.util.*

class RegisterFragment : Fragment() {

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

        if (edittext_password.text.toString() != edittext_password_confirm.text.toString()) {
            Toast.makeText(context, "Passwörter stimmen nicht überein", Toast.LENGTH_SHORT)
                .show()
        } else {
            with(api) {
                authControllerRegister(
                    RegisterPayload()
                        .email(edittext_email.text.toString())
                        .firstName(edittext_first_name.text.toString())
                        .lastName(edittext_surname.text.toString())
                        .password(edittext_password.text.toString())
                        .role(RegisterPayload.RoleEnum.HELPER)
                )
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { response ->
                        context?.let {
                            Preferences.setToken(it, response.accessToken)
                            Preferences.setUserId(it, response.id)
                            api.setBearerToken(response.accessToken)
                        }
                        button_data_protection.setOnClickListener {
                            Toast.makeText(context, "In Entwicklung", Toast.LENGTH_SHORT).show()
                        }
                    }
            }
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
            val action =
                RegisterFragmentDirections.actionRegisterFragmentToRegisterDetailedFragment(
                    firstname,
                    lastname,
                    email,
                    password
                )
            findNavController().navigate(action)
        }
    }

}
