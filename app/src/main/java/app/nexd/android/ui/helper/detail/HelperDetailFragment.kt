package app.nexd.android.ui.helper.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import app.nexd.android.R
import app.nexd.android.api.model.HelpRequestArticle
import app.nexd.android.databinding.FragmentHelperRequestDetailBinding
import app.nexd.android.ui.common.DefaultSnackbar
import app.nexd.android.ui.common.HelpRequestArticleBinder
import app.nexd.android.ui.helper.detail.HelperDetailViewModel.Progress.*
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_helper_request_detail.*
import mva2.adapter.ListSection
import mva2.adapter.MultiViewAdapter


class HelperDetailFragment : Fragment() {


    private val viewModel: HelperDetailViewModel by viewModels()

    private lateinit var binding: FragmentHelperRequestDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHelperRequestDetailBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args: HelperDetailFragmentArgs by navArgs()

        val adapter = MultiViewAdapter()
        recyclerView_requests.adapter = adapter
        recyclerView_requests.layoutManager = LinearLayoutManager(context)

        adapter.registerItemBinders(
            HelpRequestArticleBinder()
        )

        viewModel.requestDetails(args.requestId).observe(viewLifecycleOwner, Observer { request ->
            binding.requestId = request.id
            adapter.removeAllSections()

            textView_name.text = context?.getString(
                R.string.user_name_layout,
                request.requester?.firstName
                , request.requester?.lastName
            )

            val list = ListSection<HelpRequestArticle>()
            list.addAll(request.articles ?: emptyList())
            adapter.addSection(list)

            if (request.helpListId != null) {
                accept.isEnabled = false
                accept.text = getString(R.string.helper_request_detail_button_accepted)
            } else {
                accept.isEnabled = true
                accept.text = getString(R.string.helper_request_detail_button_accept)
            }
        })

        viewModel.progress.observe(viewLifecycleOwner, Observer { progress ->
            when (progress) {
                is Idle -> {}
                is Error -> {
                    DefaultSnackbar(view, progress.message, Snackbar.LENGTH_SHORT)
                }
                is Finished -> {
                    findNavController().navigateUp()
                }
            }
        })
    }

}
