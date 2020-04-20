package app.nexd.android.ui.seeker.type

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import app.nexd.android.R
import app.nexd.android.ui.seeker.type.SeekerTypeFragmentDirections.Companion.toPhoneCallFragment
import app.nexd.android.ui.seeker.type.SeekerTypeFragmentDirections.Companion.toSeekerOverviewFragment
import kotlinx.android.synthetic.main.fragment_seeker_type.*

class SeekerTypeFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_seeker_type, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button_help_request.setOnClickListener {
            findNavController().navigate(toSeekerOverviewFragment())
        }

        button_phone_call.setOnClickListener {
            findNavController().navigate(toPhoneCallFragment())
        }

        seeker_type_toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

}