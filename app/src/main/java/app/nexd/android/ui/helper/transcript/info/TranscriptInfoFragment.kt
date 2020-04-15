package app.nexd.android.ui.helper.transcript.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import app.nexd.android.R
import app.nexd.android.databinding.FragmentTranscriptInfoBinding
import app.nexd.android.ui.helper.transcript.TranscriptViewModel
import app.nexd.android.ui.helper.transcript.info.TranscriptInfoFragmentDirections.Companion.actionTranscriptInfoFragmentToTranscriptArticlesFragment
import kotlinx.android.synthetic.main.fragment_transcript_info.*

class TranscriptInfoFragment : Fragment(), NavController.OnDestinationChangedListener {

    private val viewModel: TranscriptViewModel by navGraphViewModels(R.id.nav_transcript)

    private lateinit var binding: FragmentTranscriptInfoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTranscriptInfoBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button_confirm.setOnClickListener {
            if (viewModel.validateInfo()) {
                findNavController().navigate(
                    actionTranscriptInfoFragmentToTranscriptArticlesFragment()
                )
            }
        }

        findNavController().addOnDestinationChangedListener(this)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.viewModel = viewModel

        viewModel.call.observe(viewLifecycleOwner, Observer {
            if (it == null) {
                findNavController().popBackStack(R.id.callOverviewFragment, false)
            }
        })
    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
        if (destination.id == R.id.callOverviewFragment) {
            viewModel.cancelTranscription()
        }
    }


}
