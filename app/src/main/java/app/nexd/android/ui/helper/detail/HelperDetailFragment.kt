package app.nexd.android.ui.helper.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import app.nexd.android.R
import app.nexd.android.api.model.HelpRequest
import app.nexd.android.api.model.HelpRequestArticle
import app.nexd.android.databinding.FragmentHelperRequestDetailBinding
import app.nexd.android.ui.common.HelpRequestArticleBinder
import mva2.adapter.ListSection
import mva2.adapter.MultiViewAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel


class HelperDetailFragment : Fragment() {

    private val args: HelperDetailFragmentArgs by navArgs()

    private val viewModel: HelperDetailViewModel by viewModel()

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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val adapter = MultiViewAdapter()
        binding.recyclerViewRequests.adapter = adapter
        binding.recyclerViewRequests.layoutManager = LinearLayoutManager(context)

        adapter.registerItemBinders(
            HelpRequestArticleBinder()
        )

        viewModel.requestDetails(args.requestId)
            .observe(viewLifecycleOwner, Observer { request: HelpRequest ->
                adapter.removeAllSections()

                binding.textViewName.text = resources.getString(
                    R.string.user_name_layout,
                    request.firstName,
                    request.lastName
                )

                val list = ListSection<HelpRequestArticle>()
                request.articles?.let {
                    list.addAll(it)
                }
                adapter.addSection(list)

                setAccepted(request.helpListId != null)

                binding.accept.setOnClickListener {
                    request.id?.let {
                        viewModel.acceptRequest(it)
                        findNavController().popBackStack()
                    }
                }
            })
    }

    private fun setAccepted(accepted: Boolean) {
        binding.accept.text =
            getString(if (accepted) R.string.helper_request_detail_button_accepted else R.string.helper_request_detail_button_accept)
        binding.accept.isEnabled = !accepted
    }

}
