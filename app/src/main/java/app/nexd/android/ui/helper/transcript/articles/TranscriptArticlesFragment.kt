package app.nexd.android.ui.helper.transcript.articles

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import app.nexd.android.R
import app.nexd.android.databinding.FragmentTranscriptArticlesBinding
import app.nexd.android.di.sharedGraphViewModel
import app.nexd.android.ui.common.HelpRequestCreateArticleBinder
import app.nexd.android.ui.helper.transcript.TranscriptViewModel
import app.nexd.android.ui.helper.transcript.TranscriptViewModel.Progress.Finished
import app.nexd.android.ui.helper.transcript.articles.TranscriptArticlesFragmentDirections.Companion.toTranscriptSummaryFragment
import mva2.adapter.ListSection
import mva2.adapter.MultiViewAdapter

class TranscriptArticlesFragment : Fragment() {

    private val transcriptViewModel: TranscriptViewModel by sharedGraphViewModel(R.id.nav_transcript)

    private lateinit var binding: FragmentTranscriptArticlesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTranscriptArticlesBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = transcriptViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = MultiViewAdapter()
        adapter.registerItemBinders(HelpRequestCreateArticleBinder())

        binding.recyclerviewHelpRequestArticles.adapter = adapter
        binding.recyclerviewHelpRequestArticles.layoutManager = LinearLayoutManager(context)

        transcriptViewModel.articles.observe(viewLifecycleOwner, Observer { articles ->
            adapter.removeAllSections()

            val section = ListSection<HelpRequestCreateArticleBinder.ArticleInput>()
            section.addAll(articles)
            adapter.addSection(section)
        })

        binding.buttonTranscript.setOnClickListener {
            transcriptViewModel.saveHelpRequest()
        }

        transcriptViewModel.progress.observe(viewLifecycleOwner,  Observer {
            when (it) {
                Finished -> findNavController().navigate(toTranscriptSummaryFragment())
                // TODO: handle other states
            }
        })
    }

    override fun onPause() {
        super.onPause()
        binding.playerView.player?.playWhenReady = false
    }

    override fun onResume() {
        super.onResume()
        binding.playerView.player?.playWhenReady = true
    }


}