package de.andrestefanov.android.nearbuy.ui.auth

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import de.andrestefanov.android.nearbuy.Preferences
import de.andrestefanov.android.nearbuy.R
import de.andrestefanov.android.nearbuy.api.network.RestClient
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.auth_fragment.*
import kotlinx.android.synthetic.main.auth_fragment.button_login
import kotlinx.android.synthetic.main.fragment_login.*

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button_login.setOnClickListener {
            with(RestClient()) {
                login(
                    edittext_email.text.toString(),
                    edittext_password.text.toString()
                ).subscribe(
                    { loginResponse ->
                        context?.let {
                            Preferences.setToken(it, loginResponse.accessToken)
                            Preferences.setUserId(it, loginResponse.id)
                        }

                        findNavController().navigate(R.id.action_loginFragment_to_roleFragment)
                    },
                    {
                        Log.e(LoginFragment::class.simpleName, "Login failed", it)
                    }
                )
            }
        }
    }
}
