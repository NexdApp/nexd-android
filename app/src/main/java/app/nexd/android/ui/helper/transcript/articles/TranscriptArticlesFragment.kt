package app.nexd.android.ui.helper.transcript.articles

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import app.nexd.android.api.model.CreateHelpRequestArticleDto
import app.nexd.android.api.model.HelpRequestCreateDto
import app.nexd.android.databinding.FragmentTranscriptArticlesBinding
import app.nexd.android.ui.common.HelpRequestCreateArticleBinder
import kotlinx.android.synthetic.main.fragment_transcript_articles.*
import mva2.adapter.ListSection
import mva2.adapter.MultiViewAdapter

class TranscriptArticlesFragment : Fragment() {

    private val viewModel: CallTranslateViewModel by viewModels()

    private lateinit var binding: FragmentTranscriptArticlesBinding

    private val adapter = MultiViewAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTranscriptArticlesBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView_requests.adapter = adapter
        recyclerView_requests.layoutManager = LinearLayoutManager(context)
        adapter.registerItemBinders(
            HelpRequestCreateArticleBinder()
        )

        seekBar_slider.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser)
                    viewModel.setPlaybackPosition(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })

        val args: CallTranslateFragmentArgs by navArgs()

        viewModel.downloadAudioFile(args.callRequestId)

        viewModel.getCall(args.callRequestId).observe(viewLifecycleOwner, Observer { call ->
            viewModel.getArticles().observe(viewLifecycleOwner, Observer { articles ->
                adapter.removeAllSections()
                val articlesSection = ListSection<HelpRequestCreateArticleBinder.ArticleInput>()
                val articlesInput = articles.map { HelpRequestCreateArticleBinder.ArticleInput(it) }
                articlesSection.addAll(articlesInput)

                adapter.addSection(articlesSection)

                button_transcript.setOnClickListener {
                    val request = HelpRequestCreateDto()
                        .articles(articlesInput
                            .filter { it.amount > 0 }
                            .map {
                                CreateHelpRequestArticleDto()
                                    .articleCount(it.amount)
                                    .articleId(it.article.id)

                            })
                    /*.street(currentUser.street)
                    .number(currentUser.number)
                    .zipCode(currentUser.zipCode)
                    .city(currentUser.city)
                    .phoneNumber(currentUser.telephone)
                    .additionalRequest(textView_additionalRequest.text.toString())*/
                    viewModel.convertToHelpRequest(call.sid, request)
                        .observe(viewLifecycleOwner, Observer {
                            findNavController().navigateUp()
                        })
                }
            })

        })
    }

    override fun onPause() {
        super.onPause()
        viewModel.pausePlayback()
    }

}