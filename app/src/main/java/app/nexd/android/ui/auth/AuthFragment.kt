package app.nexd.android.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import app.nexd.android.R
import kotlinx.android.synthetic.main.fragment_auth.*

class AuthFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_auth, container, false)
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
