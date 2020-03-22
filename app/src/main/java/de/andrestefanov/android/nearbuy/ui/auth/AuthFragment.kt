package de.andrestefanov.android.nearbuy.ui.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import de.andrestefanov.android.nearbuy.Preferences

import de.andrestefanov.android.nearbuy.R
import kotlinx.android.synthetic.main.auth_fragment.*

class AuthFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.auth_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        context?.let {
            Preferences.getToken(it)?.let {
                findNavController().navigate(AuthFragmentDirections.actionAuthFragmentToRoleFragment())
            }
        }

        button_login.setOnClickListener {
            findNavController().navigate(AuthFragmentDirections.actionAuthFragmentToLoginFragment())
        }

        button_register.setOnClickListener {
            findNavController().navigate(AuthFragmentDirections.actionAuthFragmentToRegisterFragment())
        }
    }

}
