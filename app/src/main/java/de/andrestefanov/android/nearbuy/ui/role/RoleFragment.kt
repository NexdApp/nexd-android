package de.andrestefanov.android.nearbuy.ui.role

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import de.andrestefanov.android.nearbuy.R
import kotlinx.android.synthetic.main.role_fragment.*

class RoleFragment : Fragment() {

    companion object {
        fun newInstance() =
            RoleFragment()
    }

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


        role_button_helper.setOnClickListener {
            findNavController().navigate(R.id.helperOverviewFragment)
        }

        role_button_requester.setOnClickListener {
            findNavController().navigate(R.id.requesterOverviewFragment)
        }
    }

}
