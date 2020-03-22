package de.andrestefanov.android.nearbuy.ui.role

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import de.andrestefanov.android.nearbuy.R
import de.andrestefanov.android.nearbuy.ui.view.SelectDialog
import kotlinx.android.synthetic.main.role_fragment.*

class RoleFragment : Fragment() {

    private lateinit var viewModel: RoleViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.role_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RoleViewModel::class.java)

        role_button_buyer.setOnClickListener {
            findNavController().navigate(R.id.action_roleFragment_to_helperOverviewFragment)
        }

        role_button_seeker.setOnClickListener {
            findNavController().navigate(R.id.action_roleFragment_to_requesterOverviewFragment)
        }
    }

}
