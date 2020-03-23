package de.andrestefanov.android.nearbuy.ui.auth

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import de.andrestefanov.android.nearbuy.Preferences
import de.andrestefanov.android.nearbuy.R
import de.andrestefanov.android.nearbuy.api
import de.andrestefanov.android.nearbuy.api.model.RegisterPayload
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.fragment_register.*

class RegisterFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button_register.setOnClickListener {

            if (edittext_password.text.toString() != edittext_password_confirm.text.toString()) {
                Toast.makeText(context, "Passwörter stimmen nicht überein", Toast.LENGTH_SHORT)
                    .show()
            } else {
                with(api) {
                    authControllerRegister(
                        RegisterPayload()
                            .email(edittext_email.text.toString())
                            .firstName(edittext_first_name.text.toString())
                            .lastName(edittext_surname.text.toString())
                            .password(edittext_password.text.toString())
                            .role(RegisterPayload.RoleEnum.HELPER))
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                            { response ->
                                context?.let {
                                    Preferences.setToken(it, response.accessToken)
                                    Preferences.setUserId(it, response.id)
                                    api.setBearerToken(response.accessToken)
                                }

                                findNavController().navigate(R.id.action_registerFragment_to_roleFragment)
                            },
                            {
                                Log.e(RegisterFragment::class.simpleName, "register failed", it)
                                Toast.makeText(
                                    context,
                                    "Registrierung fehlgeschlagen",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        )
                }
            }
        }
    }

}
