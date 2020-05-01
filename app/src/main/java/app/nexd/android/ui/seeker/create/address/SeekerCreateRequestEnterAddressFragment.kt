package app.nexd.android.ui.seeker.create.address

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import app.nexd.android.R
import app.nexd.android.di.sharedGraphViewModel
import app.nexd.android.ui.seeker.create.SeekerCreateRequestViewModel
import app.nexd.android.ui.seeker.create.articles.SeekerCreateRequestEnterArticlesFragment
import kotlinx.android.synthetic.main.fragment_seeker_create_request_confirm_address.*

class SeekerCreateRequestEnterAddressFragment : Fragment() {
    private val vm: SeekerCreateRequestViewModel by sharedGraphViewModel(R.id.nav_seeker_create_request)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_seeker_create_request_confirm_address,
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

            firstName = vm.requestToConfirm?.firstName ?: currentUser.firstName ?: ""
            lastName = vm.requestToConfirm?.lastName ?: currentUser.lastName ?: ""
            street = vm.requestToConfirm?.street ?: currentUser.street ?: ""
            streetNumber = vm.requestToConfirm?.number ?: currentUser.number ?: ""
            zipCode = vm.requestToConfirm?.zipCode ?: currentUser.zipCode ?: ""
            city = vm.requestToConfirm?.city ?: currentUser.city ?: ""
            phoneNumber = vm.requestToConfirm?.phoneNumber ?: currentUser.phoneNumber ?: ""

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

                    vm.requestToConfirm!!.firstName(firstName)
                        .lastName(lastName)
                        .street(street)
                        .number(streetNumber)
                        .zipCode(zipCode)
                        .city(city)
                        .phoneNumber(phoneNumber)

                    vm.sendRequest()
                }
            }
        })

        vm.state().observe(viewLifecycleOwner, Observer {
            when (it) {
                SeekerCreateRequestViewModel.State.FINISHED -> {
                    Toast.makeText(
                        requireContext(),
                        "Request successfully submitted",
                        Toast.LENGTH_SHORT
                    ).show()
                    findNavController().navigate(R.id.action_seekerCreateRequestEnterAddressFragment_to_seekerOverviewFragment)
                }
                else -> Log.d(
                    SeekerCreateRequestEnterArticlesFragment::class.simpleName,
                    "unhandled state $it"
                )
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