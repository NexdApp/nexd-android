package app.nexd.android.ui.seeker.create.articles

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import app.nexd.android.R
import app.nexd.android.databinding.FragmentSeekerCreateRequestEnterArticlesBinding
import app.nexd.android.di.sharedGraphViewModel
import app.nexd.android.ui.common.HelpRequestCreateArticleBinder
import app.nexd.android.ui.seeker.create.SeekerCreateRequestViewModel
import app.nexd.android.ui.seeker.create.SeekerCreateRequestViewModel.State.PROCESSING
import mva2.adapter.ListSection
import mva2.adapter.MultiViewAdapter

class SeekerCreateRequestEnterArticlesFragment : Fragment() {

    private val vm: SeekerCreateRequestViewModel by sharedGraphViewModel(R.id.nav_seeker_create_request)

    private lateinit var adapter: MultiViewAdapter
    private lateinit var binding: FragmentSeekerCreateRequestEnterArticlesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            FragmentSeekerCreateRequestEnterArticlesBinding.inflate(inflater, container, false)
        binding.viewModel = vm
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.recyclerViewRequests.layoutManager = LinearLayoutManager(context)

        adapter = MultiViewAdapter()
        binding.recyclerViewRequests.adapter = adapter

        adapter.registerItemBinders(HelpRequestCreateArticleBinder())


        vm.getArticleListSection()
        vm.setUserInfo()

        vm.articles.observe(viewLifecycleOwner, Observer { articles ->
            adapter.removeAllSections()
            val articleListSection = ListSection<HelpRequestCreateArticleBinder.ArticleInput>()
            articleListSection.addAll(articles)
            adapter.addSection(articleListSection)

            binding.buttonAccept.setOnClickListener {
                if (!vm.selectedArticlesListIsNotEmpty()) {
                    // show error message
                }
            }
        })

        vm.state().observe(viewLifecycleOwner, Observer {
            when (it) {
                PROCESSING -> {
                    findNavController().navigate(SeekerCreateRequestEnterArticlesFragmentDirections.toSeekerCreateRequestConfirmAddressFragment())
                }
                else -> Log.d(
                    SeekerCreateRequestEnterArticlesFragment::class.simpleName,
                    "unhandled state $it"
                )
            }

        })
    }
}
