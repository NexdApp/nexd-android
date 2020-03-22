package de.andrestefanov.android.nearbuy.ui.auth

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import de.andrestefanov.android.nearbuy.Preferences
import de.andrestefanov.android.nearbuy.R
import de.andrestefanov.android.nearbuy.api.network.RestClient
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.auth_fragment.*
import kotlinx.android.synthetic.main.auth_fragment.button_login
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        edittext_password.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                login()
            }
            false
        }

        button_login.setOnClickListener {
            login()
        }
    }

    private fun login() {
        with(RestClient.INSTANCE) {
            login(
                edittext_email.text.toString(),
                edittext_password.text.toString()
            ).subscribe(
                { loginResponse ->
                    context?.let {
                        Preferences.setToken(it, loginResponse.accessToken)
                        Preferences.setUserId(it, loginResponse.id)
                    }

                    findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRoleFragment())
                },
                {
                    activity?.runOnUiThread {
                        Snackbar.make(edittext_email, "Login fehlgeschlagen", Snackbar.LENGTH_SHORT).show()
                    }
                    Log.e(LoginFragment::class.simpleName, "Login failed", it)
                }
            )
        }
    }
}
