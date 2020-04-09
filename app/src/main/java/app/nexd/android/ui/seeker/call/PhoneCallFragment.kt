package app.nexd.android.ui.seeker.call

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import app.nexd.android.R
import kotlinx.android.synthetic.main.fragment_phone_call.*

class PhoneCallFragment : Fragment() {

    private val viewModel: PhoneCallViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_phone_call, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getPhoneNumber().observe(viewLifecycleOwner, Observer { phoneNumber ->
            textView_details.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                Html.fromHtml(
                    getString(R.string.seeker_phone_call_text, phoneNumber),
                    Html.FROM_HTML_MODE_LEGACY
                )
            } else {
                Html.fromHtml(
                    getString(R.string.seeker_phone_call_text, phoneNumber)
                )
            }
        })
    }

}