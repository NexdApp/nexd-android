package app.nexd.android.ui.seeker.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import app.nexd.android.R
import app.nexd.android.api.model.HelpRequestArticle
import app.nexd.android.ui.common.HelpRequestArticleBinder
import app.nexd.android.ui.utils.livedata.observe
import kotlinx.android.synthetic.main.fragment_seeker_detail.*
import mva2.adapter.ListSection
import mva2.adapter.MultiViewAdapter

class SeekerDetailFragment : Fragment() {

    private val viewModel: SeekerDetailViewModel by viewModels()
    private val args: SeekerDetailFragmentArgs by navArgs()

    private val articlesAdapter = MultiViewAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_seeker_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        recyclerView_requests.adapter = articlesAdapter
        recyclerView_requests.layoutManager = LinearLayoutManager(context)

        articlesAdapter.registerItemBinders(
            HelpRequestArticleBinder()
        )

        viewModel.progress.observe(viewLifecycleOwner) {
            when (it) {
                is SeekerDetailViewModel.Progress.Idle -> {
                }
                is SeekerDetailViewModel.Progress.Error -> {
                    Toast.makeText(requireContext(), getText(R.string.helper_request_detail_message_error_on_cancellation), Toast.LENGTH_LONG).show()
                }
                is SeekerDetailViewModel.Progress.Canceled -> {
                    // show information
                    Toast.makeText(requireContext(), getText(R.string.helper_request_detail_message_cancelled), Toast.LENGTH_LONG).show()
                    findNavController().navigateUp()
                }
            }
        }

        viewModel.getRequest(args.requestId).observe(viewLifecycleOwner) { request ->
            articlesAdapter.removeAllSections()
            val articlesList = ListSection<HelpRequestArticle>()
            articlesList.addAll(request.articles!!)
            articlesAdapter.addSection(articlesList)

            textView_additionalRequest_label.visibility =
                if (request.additionalRequest.isNullOrBlank()) View.GONE else View.VISIBLE
            textView_additionalRequest.text = request.additionalRequest

            button_cancel.setOnClickListener {
                viewModel.cancelRequest(request)
            }
        }
    }

}