package de.andrestefanov.android.nearbuy.ui.auth

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import de.andrestefanov.android.nearbuy.Preferences
import de.andrestefanov.android.nearbuy.R
import de.andrestefanov.android.nearbuy.api.network.RestClient
import kotlinx.android.synthetic.main.fragment_register_detailed.*

class RegisterDetailedFragment : Fragment() {

    private val args: RegisterDetailedFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_register_detailed, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        edittext_location.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                register()
            }
            false
        }

        button_register.setOnClickListener {
            register()
        }

        button_data_protection.setOnClickListener {
            Toast.makeText(context, "In Entwicklung", Toast.LENGTH_SHORT).show()
        }
    }

    private fun register() {
        val phonenumber = edittext_phonenumber.text.toString().trim()
        val location = edittext_location.text.toString().trim()
        val dataprotection = checkbox_data_protection.isChecked

        var successful = true

        if (phonenumber.isEmpty()) {
            successful = false
            edittext_phonenumber.error = "Bitte ausfüllen"
        }

        if (location.isEmpty()) {
            successful = false
            edittext_location.error = "Bitte ausfüllen"
        }

        if (!dataprotection) {
            successful = false
            checkbox_data_protection.error = "Bestätigen"
        }

        if (!successful)
            return

        findNavController().navigate(RegisterDetailedFragmentDirections.actionRegisterDetailedFragmentToRoleFragment())
        return

        with(RestClient.INSTANCE) {
            register(
                firstName = args.firstname,
                lastName = args.lastname,
                email = args.email,
                password = args.password
            )
                .andThen(
                    login(
                        args.email,
                        args.password
                    )
                )
                .subscribe(
                    { loginResponse ->
                        context?.let {
                            Preferences.setToken(it, loginResponse.accessToken)
                            Preferences.setUserId(it, loginResponse.id)
                        }

                        findNavController().navigate(RegisterDetailedFragmentDirections.actionRegisterDetailedFragmentToRoleFragment())

                    },
                    {
                        Log.e(RegisterFragment::class.simpleName, "register failed", it)
                        activity?.runOnUiThread {
                            Snackbar.make(
                                edittext_phonenumber,
                                "Registrierung fehlgeschlagen",
                                Snackbar.LENGTH_SHORT
                            ).show()
                        }

                    }
                )
        }
    }
}
