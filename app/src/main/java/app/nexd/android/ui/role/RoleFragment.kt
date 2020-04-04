package app.nexd.android.ui.role

import android.os.Bundle
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import app.nexd.android.R
import kotlinx.android.synthetic.main.fragment_role.*

class RoleFragment : Fragment() {

    private val viewModel: RoleViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_role, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getMe().observe(viewLifecycleOwner, Observer { currentUser ->
            val startingString = "Welcome, "
            val fullTitle = startingString + currentUser.firstName
            val title = SpannableString(fullTitle)
            title.setSpan(
                ForegroundColorSpan(
                    ContextCompat.getColor(
                        context!!,
                        R.color.colorPrimaryDark
                    )
                ),
                startingString.length,
                fullTitle.length, 0
            )

            textView_title.setText(title, TextView.BufferType.SPANNABLE)
        })

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
