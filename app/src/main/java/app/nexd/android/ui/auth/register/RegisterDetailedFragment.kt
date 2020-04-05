package app.nexd.android.ui.auth.register

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
import kotlinx.android.synthetic.main.fragment_register_detailed.*

class RegisterDetailedFragment : Fragment() {

    private val args: RegisterDetailedFragmentArgs by navArgs()

    private val viewModel: RegisterDetailedViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_register_detailed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editText_city.setOnEditorActionListener { _, i, _ ->
            if (i == EditorInfo.IME_ACTION_DONE) {
                register()
            }
            false
        }

        button_register.setOnClickListener {
            register()
        }
    }

    fun register() {
        val phoneNumber = editText_phoneNumber.text
        val streetName = editText_streetName.text
        val houseNumber = editText_houseNumber.text
        val zipCode = editText_zipCode.text
        val city = editText_city.text
        var success = true

        if (phoneNumber.isNullOrEmpty()) {
            editText_phoneNumber.error = resources.getString(R.string.error_message_user_detail_field_missing)
            success = false
        }

        if (streetName.isNullOrEmpty()) {
            editText_streetName.error = resources.getString(R.string.error_message_user_detail_field_missing)
            success = false
        }

        if (houseNumber.isNullOrEmpty()) {
            editText_houseNumber.error = resources.getString(R.string.error_message_user_detail_field_missing)
            success = false
        }

        if (zipCode.isNullOrEmpty()) {
            editText_zipCode.error = resources.getString(R.string.error_message_user_detail_field_missing)
            success = false
        }

        if (city.isNullOrEmpty()) {
            editText_city.error = resources.getString(R.string.error_message_user_detail_field_missing)
            success = false
        }

        if (success) {
            viewModel.register(
                args.firstName,
                args.lastName,
                args.email,
                args.password,
                editText_phoneNumber.text.toString(),
                editText_streetName.text.toString(),
                editText_houseNumber.text.toString(),
                editText_zipCode.text.toString(),
                editText_city.text.toString()
            ).observe(viewLifecycleOwner, Observer {
                findNavController().navigate(RegisterDetailedFragmentDirections.toRoleFragment())
            })
        }
    }

}