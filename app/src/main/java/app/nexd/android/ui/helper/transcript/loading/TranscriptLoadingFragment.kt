package app.nexd.android.ui.helper.transcript.loading

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import app.nexd.android.R
import app.nexd.android.databinding.FragmentTranscriptLoadingBinding
import app.nexd.android.ui.helper.transcript.TranscriptViewModel
import app.nexd.android.di.sharedGraphViewModel

class TranscriptLoadingFragment: Fragment() {

    private val transcriptViewModel: TranscriptViewModel by sharedGraphViewModel(R.id.nav_transcript)

    private lateinit var binding: FragmentTranscriptLoadingBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTranscriptLoadingBinding.inflate(inflater, container, false)
        binding.viewModel = transcriptViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        transcriptViewModel.transcriptCall()
        transcriptViewModel.call.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                findNavController().navigate(
                    TranscriptLoadingFragmentDirections.toTranscriptInfoFragment()
                )
            }
        })
    }
}