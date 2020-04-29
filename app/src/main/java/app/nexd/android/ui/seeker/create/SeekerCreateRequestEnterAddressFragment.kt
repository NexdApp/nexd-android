package app.nexd.android.ui.seeker.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import app.nexd.android.R
import kotlinx.android.synthetic.main.fragment_seeker_create_request_enter_address.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class SeekerCreateRequestEnterAddressFragment : Fragment() {
    private val vm: SeekerCreateRequestViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_seeker_create_request_enter_address,
            container,
            false
        )
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        lateinit var firstName: String
        lateinit var lastName: String
        lateinit var street: String
        lateinit var streetNumber: String
        lateinit var zipCode: String
        lateinit var city: String
        lateinit var phoneNumber: String


        vm.getCurrentUser().observe(viewLifecycleOwner, Observer { currentUser ->
            firstName = currentUser.firstName ?: ""
            lastName = currentUser.lastName ?: ""
            street = currentUser.street ?: ""
            streetNumber = currentUser.number ?: ""
            zipCode = currentUser.zipCode ?: ""
            city = currentUser.city ?: ""
            phoneNumber = currentUser.phoneNumber ?: ""

            editText_first_name.setText(firstName)
            editText_last_name.setText(lastName)
            editText_street.setText(street)
            editText_street_number.setText(streetNumber)
            editText_zip_code.setText(zipCode)
            editText_city.setText(city)
            editText_phoneNumber.setText(phoneNumber)


            button_confirm.setOnClickListener {
                if (!editText_first_name.isEmptyShowError() ||
                    !editText_last_name.isEmptyShowError() ||
                    !editText_street.isEmptyShowError() ||
                    !editText_street_number.isEmptyShowError() ||
                    !editText_zip_code.isEmptyShowError() ||
                    !editText_city.isEmptyShowError() ||
                    !editText_phoneNumber.isEmptyShowError()
                ) {
                    firstName = editText_first_name.text.toString()
                    lastName = editText_last_name.text.toString()
                    street = editText_street.text.toString()
                    streetNumber = editText_street_number.text.toString()
                    zipCode = editText_zip_code.text.toString()
                    city = editText_city.text.toString()
                    phoneNumber = editText_phoneNumber.text.toString()

                    vm.requestToConfirm.firstName(firstName)
                        .lastName(lastName)
                        .street(street)
                        .number(streetNumber)
                        .zipCode(zipCode)
                        .city(city)
                        .phoneNumber(phoneNumber)
                }
            }
        })

    }

    private fun EditText.isEmptyShowError(): Boolean {
        if (this.text.isBlank()) {
            this.error = getString(R.string.error_message_user_detail_field_missing)
            return true
        }
        return false
    }


}