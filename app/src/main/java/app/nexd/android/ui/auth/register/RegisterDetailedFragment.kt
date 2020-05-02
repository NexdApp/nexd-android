package app.nexd.android.ui.auth.register

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import app.nexd.android.R
import app.nexd.android.ui.auth.register.model.Effect
import app.nexd.android.ui.auth.register.model.ErrorState
import app.nexd.android.ui.auth.register.model.Event
import app.nexd.android.ui.auth.register.model.Loading
import app.nexd.android.ui.auth.register.model.ValidationError
import app.nexd.android.ui.auth.register.model.ViewState
import app.nexd.android.ui.common.Constants
import app.nexd.android.ui.common.DefaultSnackbar
import app.nexd.android.ui.utils.setDoneListener
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_register_detailed.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterDetailedFragment : Fragment(R.layout.fragment_register_detailed) {

    private val viewModel: RegisterDetailedViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupNavController()
        setupListener()
        viewModel.viewState.observe(viewLifecycleOwner, ::renderViewState)
        viewModel.effects.observe(viewLifecycleOwner, ::handleEffects)
    }

    private fun handleEffects(effect: Effect) {
        when (effect) {
            is Effect.NavigateToRoleView -> navigateToRoleFragment()
            is Effect.ShowErrorMessage -> showSnackBar(effect.message)
        }
    }

    private fun setupListener() {
        editText_locality.setDoneListener(::tryRegister)
        button_register.setOnClickListener { tryRegister() }
        button_dataProtection_detail_registration.setOnClickListener { showPrivacyPolicy() }
    }

    private fun setupNavController() {
        with(findNavController()) {
            val appBarConfiguration = AppBarConfiguration(setOf(R.id.registerDetailedFragment))
            register_detailed_toolbar.setupWithNavController(this, appBarConfiguration)
        }
    }

    private fun navigateToRoleFragment() {
        findNavController().navigate(RegisterDetailedFragmentDirections.toRoleFragment())
    }

    private fun showSnackBar(message: Int) {
        view?.let { DefaultSnackbar(it, getString(message), Snackbar.LENGTH_SHORT) }
    }

    private fun renderViewState(viewState: ViewState) {
        renderLoading(viewState.loading)
        renderError(viewState.errorState)
    }

    private fun renderLoading(loading: Loading) {
        when (loading) {
            Loading.IsLoading -> showLoading()
            Loading.NotLoading -> hideLoading()
        }
    }

    private fun hideLoading() {
        progressBar.visibility = View.GONE
        editText_phoneNumber.isEnabled = true
        editText_street.isEnabled = true
        editText_houseNr.isEnabled = true
        editText_zipCode.isEnabled = true
        editText_locality.isEnabled = true
    }

    private fun showLoading() {
        progressBar.visibility = View.VISIBLE
        editText_phoneNumber.isEnabled = false
        editText_street.isEnabled = false
        editText_houseNr.isEnabled = false
        editText_zipCode.isEnabled = false
        editText_locality.isEnabled = false
    }

    private fun renderError(errorState: ErrorState) {
        when(errorState) {
            is ErrorState.NoError -> hideValidationErrors()
            is ErrorState.FormValidationErrors -> renderValidationErrors(errorState.validationErrors)
        }
    }

    private fun hideValidationErrors() {
        editText_phoneNumber.error = null
        editText_houseNr.error = null
        editText_zipCode.error = null
        editText_locality.text = null
        editText_street.text = null
    }

    private fun renderValidationErrors(validationErrors: List<ValidationError>) {
        hideValidationErrors()
        validationErrors.forEach { error ->
            when (error) {
                is ValidationError.PhoneNumber -> showValidationError(editText_phoneNumber, error.message)
                is ValidationError.HouseNumber -> showValidationError(editText_houseNr, error.message)
                is ValidationError.Zip -> showValidationError(editText_zipCode, error.message)
                is ValidationError.Locality -> showValidationError(editText_locality, error.message)
                is ValidationError.Street -> showValidationError(editText_street, error.message)
            }
        }
    }

    private fun showValidationError(editText: EditText, @StringRes message: Int) {
        editText.error = getString(message)
    }

    private fun tryRegister() {
        viewModel.event(
            Event.UserRegistrationEvent(
                phone = editText_phoneNumber.text.toString(),
                zip = editText_zipCode.text.toString(),
                houseNumber = editText_houseNr.text.toString(),
                locality = editText_locality.text.toString(),
                street = editText_street.text.toString()
            )
        )
    }

    private fun showPrivacyPolicy() {
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse(Constants.PRIVACY_POLICY_URL)
            )
        )
    }
}