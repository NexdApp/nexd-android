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
                findNavController().navigate(R.id.action_authFragment_to_roleFragment)
            }
        }
        findNavController().navigate(R.id.action_authFragment_to_roleFragment)
        return

        button_login.setOnClickListener {
            findNavController().navigate(R.id.action_authFragment_to_loginFragment)
        }

        button_register.setOnClickListener {
            findNavController().navigate(R.id.action_authFragment_to_registerFragment)
        }
    }

}
