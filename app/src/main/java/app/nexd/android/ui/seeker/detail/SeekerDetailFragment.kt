package app.nexd.android.ui.seeker.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import app.nexd.android.R
import app.nexd.android.api.model.HelpRequestArticle
import app.nexd.android.databinding.FragmentSeekerDetailBinding
import app.nexd.android.ui.common.DefaultSnackbar
import app.nexd.android.ui.common.HelpRequestArticleBinder
import app.nexd.android.ui.utils.livedata.observe
import com.google.android.material.snackbar.Snackbar
import mva2.adapter.ListSection
import mva2.adapter.MultiViewAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class SeekerDetailFragment : Fragment() {

    private val vm: SeekerDetailViewModel by viewModel()
    private val args: SeekerDetailFragmentArgs by navArgs()

    private val articlesAdapter = MultiViewAdapter()

    private lateinit var binding: FragmentSeekerDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSeekerDetailBinding.inflate(inflater, container, false)
        binding.viewModel = vm
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        vm.setInfo(args.requestId)
        initRecyclerView()
        initVmObserver()
    }

    private fun initRecyclerView() {
        binding.recyclerViewRequests.adapter = articlesAdapter
        binding.recyclerViewRequests.layoutManager = LinearLayoutManager(context)
        articlesAdapter.registerItemBinders(
            HelpRequestArticleBinder()
        )
    }

    private fun initVmObserver() {
        vm.progress.observe(viewLifecycleOwner) {
            when (it) {
                is SeekerDetailViewModel.Progress.Idle -> {
                }
                is SeekerDetailViewModel.Progress.Error -> {
                    DefaultSnackbar(
                        binding.root,
                        R.string.helper_request_detail_message_error_on_cancellation,
                        Snackbar.LENGTH_SHORT
                    )
                }
                is SeekerDetailViewModel.Progress.Canceled -> {
                    DefaultSnackbar(
                        binding.root,
                        R.string.helper_request_detail_message_cancelled,
                        Snackbar.LENGTH_SHORT
                    )
                    findNavController().navigateUp()
                }
            }
        }

        vm.getRequest(args.requestId).observe(viewLifecycleOwner) { request ->
            articlesAdapter.removeAllSections()
            val articlesList = ListSection<HelpRequestArticle>()
            articlesList.addAll(request.articles!!)
            articlesAdapter.addSection(articlesList)

            binding.textViewAdditionalRequestLabel.visibility =
                if (request.additionalRequest.isNullOrBlank()) View.GONE else View.VISIBLE

            binding.buttonCancel.setOnClickListener {
                vm.cancelRequest(request)
            }
        }
    }

}