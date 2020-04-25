package app.nexd.android.ui.helper.transcript.summary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import app.nexd.android.R
import app.nexd.android.di.sharedGraphViewModel
import app.nexd.android.ui.helper.transcript.TranscriptViewModel
import app.nexd.android.ui.helper.transcript.summary.TranscriptSummaryFragmentDirections.Companion.toTranscriptInfoFragment
import kotlinx.android.synthetic.main.fragment_transcript_summary.*
import mva2.adapter.MultiViewAdapter

class TranscriptSummaryFragment: Fragment() {

    private val transcriptViewModel : TranscriptViewModel by sharedGraphViewModel(R.id.nav_transcript)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_transcript_summary, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        transcriptViewModel.firstName.observe(viewLifecycleOwner, Observer {
            textView_title.text = getString(R.string.transcript_summary_title, it)
        })

        button_transcript.setOnClickListener {
            transcriptViewModel.transcriptCall()
            findNavController().navigate(toTranscriptInfoFragment())
        }
    }
}