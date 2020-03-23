package de.andrestefanov.android.nearbuy.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import de.andrestefanov.android.nearbuy.R
import kotlinx.android.synthetic.main.auth_fragment.*

class AuthFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.auth_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button_login.setOnClickListener {
            findNavController().navigate(AuthFragmentDirections.actionAuthFragmentToLoginFragment())
        }

        button_register.setOnClickListener {
            findNavController().navigate(AuthFragmentDirections.actionAuthFragmentToRegisterFragment())
        }
    }

}
