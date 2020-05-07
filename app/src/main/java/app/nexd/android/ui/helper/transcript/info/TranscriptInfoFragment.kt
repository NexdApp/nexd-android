package app.nexd.android.ui.helper.transcript.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import app.nexd.android.R
import app.nexd.android.databinding.FragmentTranscriptInfoBinding
import app.nexd.android.di.sharedGraphViewModel
import app.nexd.android.ui.helper.transcript.TranscriptViewModel
import app.nexd.android.ui.helper.transcript.info.TranscriptInfoFragmentDirections.Companion.toTranscriptArticlesFragment

class TranscriptInfoFragment : Fragment() {

    private val transcriptViewModel: TranscriptViewModel by sharedGraphViewModel(R.id.nav_transcript)

    private lateinit var binding: FragmentTranscriptInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTranscriptInfoBinding.inflate(inflater, container, false)
        binding.viewModel = transcriptViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonConfirm.setOnClickListener {
            if (transcriptViewModel.validateInfo()) {
                findNavController().navigate(toTranscriptArticlesFragment())
            }
        }
    }

    override fun onPause() {
        super.onPause()
        binding.playerView.player?.playWhenReady = false
    }

    override fun onResume() {
        super.onResume()
        binding.playerView.player?.playWhenReady = true
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        transcriptViewModel.transcriptCall()
    }
}
