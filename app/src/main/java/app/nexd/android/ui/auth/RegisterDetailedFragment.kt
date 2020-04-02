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
import androidx.navigation.fragment.navArgs
import app.nexd.android.R
import app.nexd.android.ui.MainActivity
import com.google.android.material.snackbar.Snackbar
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

        edittext_city.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                register()
            }
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
        (activity as MainActivity).hideKeyboard()
        val phonenumber = edittext_phonenumber.text.toString().trim()
        val street = edittext_streetname.text.trim().toString()
        val housenumber = edittext_number.text.trim().toString()
        val zipcode = edittext_zipcode.text.toString().trim()
        val city = edittext_city.text.trim().toString()
        val dataprotection = checkbox_data_protection.isChecked

        var successful = true

        if (phonenumber.isEmpty()) {
            successful = false
            edittext_phonenumber.error = "Bitte ausfüllen"
        }

        if (street.isEmpty()) {
            successful = false
            edittext_streetname.error = "Bitte ausfüllen"
        }

        if (housenumber.isEmpty()) {
            successful = false
            edittext_number.error = "Bitte ausfüllen"
        }

        if (zipcode.isEmpty()) {
            successful = false
            edittext_zipcode.error = "Bitte ausfüllen"
        }

        if (city.isEmpty()) {
            successful = false
            edittext_city.error = "Bitte ausfüllen"
        }

        if (!dataprotection) {
            successful = false
            checkbox_data_protection.error = "Bestätigen"
        }

        if (!successful)
            return
    }
}
