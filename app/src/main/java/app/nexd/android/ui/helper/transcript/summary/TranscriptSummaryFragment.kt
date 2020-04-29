package app.nexd.android.ui.helper.transcript.summary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import app.nexd.android.R
import app.nexd.android.databinding.FragmentTranscriptSummaryBinding
import app.nexd.android.di.sharedGraphViewModel
import app.nexd.android.ui.helper.transcript.TranscriptViewModel
import app.nexd.android.ui.helper.transcript.summary.TranscriptSummaryFragmentDirections.Companion.toTranscriptLoadingFragment
import kotlinx.android.synthetic.main.fragment_transcript_summary.*

class TranscriptSummaryFragment: Fragment() {

    private val transcriptViewModel : TranscriptViewModel by sharedGraphViewModel(R.id.nav_transcript)

    private lateinit var binding: FragmentTranscriptSummaryBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTranscriptSummaryBinding.inflate(inflater, container, false)
        binding.viewModel = transcriptViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button_transcript.setOnClickListener {
            findNavController().navigate(toTranscriptLoadingFragment())
        }
    }
}