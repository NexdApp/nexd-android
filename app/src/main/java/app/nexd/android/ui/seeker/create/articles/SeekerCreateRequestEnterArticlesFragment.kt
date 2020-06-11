package app.nexd.android.ui.seeker.create.articles

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isEmpty
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import app.nexd.android.R
import app.nexd.android.databinding.FragmentSeekerCreateRequestEnterArticlesBinding
import app.nexd.android.di.sharedGraphViewModel
import app.nexd.android.ui.common.DefaultSnackBar
import app.nexd.android.ui.common.HelpRequestCreateArticleBinder
import app.nexd.android.ui.seeker.create.SeekerCreateRequestViewModel
import app.nexd.android.ui.seeker.create.SeekerCreateRequestViewModel.Progress.*
import com.google.android.material.snackbar.Snackbar
import mva2.adapter.ListSection
import mva2.adapter.MultiViewAdapter

class SeekerCreateRequestEnterArticlesFragment : Fragment() {

    private val vm: SeekerCreateRequestViewModel by sharedGraphViewModel(R.id.nav_seeker_create_request)

    private lateinit var adapter: MultiViewAdapter
    private lateinit var binding: FragmentSeekerCreateRequestEnterArticlesBinding
    private var articleListSection: ListSection<HelpRequestCreateArticleBinder.ArticleInput>? = null


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

        initUi()
        initRecyclerView()
        setVmData()
        setVmObserver()
    }

    private fun initUi() {
        binding.buttonAccept.setOnClickListener {
            vm.confirmSelectedArticles()
        }
    }

    private fun initRecyclerView() {
        binding.recyclerViewRequests.layoutManager = LinearLayoutManager(context)
        adapter = MultiViewAdapter()
        binding.recyclerViewRequests.adapter = adapter
        adapter.registerItemBinders(HelpRequestCreateArticleBinder())

    }

    private fun setVmData() {
        vm.setArticleListSection()
        vm.setUserInfo()
    }

    private fun setVmObserver() {
        vm.articles.observe(viewLifecycleOwner, Observer { articles ->
            if (binding.recyclerViewRequests.isEmpty()) {
                articleListSection = ListSection<HelpRequestCreateArticleBinder.ArticleInput>()
                articleListSection!!.addAll(articles)
                adapter.addSection(articleListSection!!)
            }
        })

        vm.progress.observe(viewLifecycleOwner, Observer {
            when (it) {
                is Idle -> {
                    //nothing to do
                }
                is Loading -> {
                    findNavController().navigate(SeekerCreateRequestEnterArticlesFragmentDirections.toSeekerCreateRequestConfirmAddressFragment())
                }
                is Finished -> {
                    // state not reachable
                }
                is Error -> {
                    it.message?.let { errorMessageId ->
                        DefaultSnackBar(binding.root, errorMessageId, Snackbar.LENGTH_SHORT)
                    }
                }
                else -> Log.d(
                    SeekerCreateRequestEnterArticlesFragment::class.simpleName,
                    "unhandled state $it"
                )
            }
        })
    }
}
