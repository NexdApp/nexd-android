package app.nexd.android.ui.helper.call

import android.os.Bundle
import android.util.Log
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
import app.nexd.android.R
import app.nexd.android.api.model.CreateHelpRequestArticleDto
import app.nexd.android.api.model.HelpRequestCreateDto
import app.nexd.android.ui.seeker.create.HelpRequestArticleBinder
import kotlinx.android.synthetic.main.fragment_translate_call.*
import mva2.adapter.ListSection
import mva2.adapter.MultiViewAdapter

class CallTranslateFragment : Fragment() {

    private val viewModel: CallTranslateViewModel by viewModels()

    private val adapter = MultiViewAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_translate_call, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView_requests.adapter = adapter
        recyclerView_requests.layoutManager = LinearLayoutManager(context)
        adapter.registerItemBinders(
            HelpRequestArticleBinder()
        )

        val args: CallTranslateFragmentArgs by navArgs()

        viewModel.getAudioFile(args.callRequestId)

        viewModel.downloadProgess.observe(viewLifecycleOwner, Observer { percentage ->
            if (percentage == 1f) {
                imageButton_toggle.visibility = View.VISIBLE
                progressBar_loading.visibility = View.GONE
            }
        })

        viewModel.maxPosition.observe(viewLifecycleOwner, Observer {
            seekBar_slider.max = it
        })

        viewModel.playbackPosition.observe(viewLifecycleOwner, Observer {
            seekBar_slider.progress = it
        })

        viewModel.isPlaying.observe(viewLifecycleOwner, Observer { isPlaying ->
            imageButton_toggle.setImageResource(
                if (isPlaying)
                    R.drawable.ic_pause_black_24dp
                else
                    R.drawable.ic_play_arrow_black_24dp
            )
        })

        imageButton_toggle.setOnClickListener {
            viewModel.togglePlayback()
        }

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



        viewModel.getCall(args.callRequestId).observe(viewLifecycleOwner, Observer { call ->
            textView_timestamp.text = call.created.toString()

            viewModel.getArticles().observe(viewLifecycleOwner, Observer { articles ->
                val articlesSection = ListSection<HelpRequestArticleBinder.ArticleInput>()
                val articlesInput = articles.map { HelpRequestArticleBinder.ArticleInput(it) }
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

}