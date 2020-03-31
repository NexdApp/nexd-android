package app.nexd.android.ui.auth

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import app.nexd.android.Preferences
import app.nexd.android.R
import app.nexd.android.api
import app.nexd.android.api.model.RegisterPayload
import com.google.android.material.snackbar.Snackbar
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
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.nexd.app/privacypage")
            )
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

        with(api) {
            authControllerRegister(
                RegisterPayload()
                    .firstName(args.firstname)
                    .lastName(args.lastname)
                    .email(args.email)
                    .password(args.password))
                .subscribe(
                    { response ->
                        context?.let {
                            Preferences.setToken(it, response.accessToken)
                            Preferences.setUserId(it, response.id)
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
