package de.andrestefanov.android.nearbuy.ui.auth

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import de.andrestefanov.android.nearbuy.Preferences
import de.andrestefanov.android.nearbuy.R
import de.andrestefanov.android.nearbuy.api.network.RestClient
import kotlinx.android.synthetic.main.buyer_request_detail_fragment.*
import kotlinx.android.synthetic.main.fragment_register.*

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
    }

    private fun register() {
        if (edittext_password.text.toString() != edittext_password_confirm.text.toString()) {
            Snackbar.make(edittext_password, "Passwörter stimmen nicht überein", Snackbar.LENGTH_SHORT).show()
        } else {
            with(RestClient.INSTANCE) {
                register(
                    firstName = edittext_first_name.text.toString(),
                    lastName = edittext_surname.text.toString(),
                    email = edittext_email.text.toString(),
                    password = edittext_password.text.toString()
                )
                    .andThen(
                        login(
                            edittext_email.text.toString(),
                            edittext_password.text.toString()
                        )
                    )
                    .subscribe(
                        { loginResponse ->
                            context?.let {
                                Preferences.setToken(it, loginResponse.accessToken)
                                Preferences.setUserId(it, loginResponse.id)
                            }

                            findNavController().navigate(R.id.action_registerFragment_to_roleFragment)
                        },
                        {
                            Log.e(RegisterFragment::class.simpleName, "register failed", it)
                            activity?.runOnUiThread {
                                Snackbar.make(
                                    edittext_email,
                                    "Registrierung fehlgeschlagen",
                                    Snackbar.LENGTH_SHORT
                                ).show()
                            }

                        }
                    )
            }
        }
    }

}
