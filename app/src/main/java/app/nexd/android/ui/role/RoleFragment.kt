package app.nexd.android.ui.role

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import app.nexd.android.NavGraphDirections
import app.nexd.android.R
import app.nexd.android.ui.AuthState
import app.nexd.android.ui.MainViewModel
import kotlinx.android.synthetic.main.fragment_role.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class RoleFragment : Fragment() {

    private val vm: RoleViewModel by viewModel()
    private val activityVm by sharedViewModel<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_role, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activityVm.authState.observe(viewLifecycleOwner, Observer {
            Log.d("YARAFANG", "auth state $it")
            when (it) {
                AuthState.UNAUTHENTICATED -> findNavController().navigate(
                    NavGraphDirections.toAuthFragmentOnAuth())
                AuthState.REGISTERED -> findNavController().navigate(
                    NavGraphDirections.toRegisterDetailedFragmentOnAuth())
                AuthState.COMPLETE -> {} // no-op
            }
        })

        vm.getMe().observe(viewLifecycleOwner, Observer { currentUser ->
            textView_title.text = getString(
                R.string.role_screen_title,
                currentUser.firstName
            )
            button_profile.text = getString(
                R.string.user_name_abbreviation,
                currentUser.firstName?.first() ?: "",
                currentUser.lastName?.first() ?: ""
            )
        })

        role_button_helper.setOnClickListener {
            findNavController().navigate(RoleFragmentDirections.toHelperTypeFragment())
        }

        role_button_seeker.setOnClickListener {
            findNavController().navigate(RoleFragmentDirections.toSeekerTypeFragment())
        }

        button_logout.setOnClickListener {
            vm.logout()
            findNavController().navigate(R.id.actionLogin)
        }
    }

}
