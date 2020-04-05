package app.nexd.android.ui.seeker.phonecall

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import app.nexd.android.R
import kotlinx.android.synthetic.main.fragment_phone_call.*

class PhoneCallFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_phone_call, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val phoneNumber = "0182/2233213"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            textView_details.text = Html.fromHtml(
                "Please call <b>$phoneNumber</b><br>to record your shopping order.",
                Html.FROM_HTML_MODE_LEGACY
            )
        } else {
            textView_details.text = "Please call $phoneNumber to record your shoppping order."
        }

    }

}