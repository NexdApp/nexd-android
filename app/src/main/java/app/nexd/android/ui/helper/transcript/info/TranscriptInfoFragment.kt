package app.nexd.android.ui.helper.transcript.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import app.nexd.android.databinding.FragmentTranscriptInfoBinding

class TranscriptInfoFragment : Fragment() {

    private lateinit var viewModel: TranscriptInfoViewModel

    private lateinit var binding: FragmentTranscriptInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTranscriptInfoBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TranscriptInfoViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
