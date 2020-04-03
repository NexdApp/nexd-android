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
import androidx.navigation.fragment.navArgs
import app.nexd.android.R
import app.nexd.android.api
import app.nexd.android.api.model.RegisterPayload
import app.nexd.android.ui.auth.register.RegisterFragment
import com.google.android.material.snackbar.Snackbar
import app.nexd.android.ui.MainActivity
import kotlinx.android.synthetic.main.fragment_register_detailed.*

class RegisterDetailedFragment : Fragment() {

    private val args: RegisterDetailedFragmentArgs by navArgs()
    private val viewModel: RegisterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_register_detailed, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        editText_city.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                register()
            }
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
        (activity as MainActivity).hideKeyboard()
        val phoneNumber = editText_phoneNumber.text.toString().trim()
        val street = editText_streetName.text.trim().toString()
        val houseNumber = editText_houseNumber.text.trim().toString()
        val zipCode = editText_zipCode.text.toString().trim()
        val city = editText_city.text.trim().toString()
        val dataProtection = checkbox_data_protection.isChecked

        var successful = true

        if (phoneNumber.isEmpty()) {
            successful = false
            editText_phoneNumber.error = getString(R.string.error_message_user_detail_field_missing)
        }

        if (street.isEmpty()) {
            successful = false
            editText_streetName.error = getString(R.string.error_message_user_detail_field_missing)
        }

        if (houseNumber.isEmpty()) {
            successful = false
            editText_houseNumber.error = getString(R.string.error_message_user_detail_field_missing)
        }

        if (zipCode.isEmpty()) {
            successful = false
            editText_zipCode.error = getString(R.string.error_message_user_detail_field_missing)
        }

        if (city.isEmpty()) {
            successful = false
            editText_city.error = getString(R.string.error_message_user_detail_field_missing)
        }

        if (!dataProtection) {
            successful = false
            checkbox_data_protection.error = getString(R.string.error_message_user_detail_field_missing)
        }

        if (!successful)
            return

        with(api) {
            authControllerRegister(
                RegisterPayload()
                    .firstName(args.firstname)
                    .lastName(args.lastname)
                    .email(args.email)
                    .password(args.password)
            )
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
