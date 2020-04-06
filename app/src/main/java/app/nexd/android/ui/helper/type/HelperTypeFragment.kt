package app.nexd.android.ui.helper.type

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import app.nexd.android.R
import app.nexd.android.ui.helper.type.HelperTypeFragmentDirections.Companion.toCallOverviewFragment
import app.nexd.android.ui.helper.type.HelperTypeFragmentDirections.Companion.toHelpRequestOverviewFragment
import kotlinx.android.synthetic.main.fragment_helper_type.*

class HelperTypeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_helper_type, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button_shopping.setOnClickListener {
            findNavController().navigate(toHelpRequestOverviewFragment())
        }

        button_transcript.setOnClickListener {
            findNavController().navigate(toCallOverviewFragment())
        }
    }

}