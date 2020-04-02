package app.nexd.android.ui.role

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import app.nexd.android.R
import kotlinx.android.synthetic.main.fragment_role.*

class RoleFragment : Fragment() {

    private lateinit var viewModel: RoleViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_role, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RoleViewModel::class.java)

        role_button_helper.setOnClickListener {
            findNavController().navigate(RoleFragmentDirections.actionRoleFragmentToHelperOverviewFragment())
        }

        role_button_seeker.setOnClickListener {
            findNavController().navigate(RoleFragmentDirections.actionRoleFragmentToRequesterOverviewFragment())
        }

        button_logout.setOnClickListener {
            viewModel.logout()
            findNavController().navigate(R.id.actionLogin)
        }
    }

}
