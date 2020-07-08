package app.nexd.android.ui.seeker.create.articles

import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import app.nexd.android.R
import app.nexd.android.databinding.FragmentSeekerCreateRequestEnterArticlesBinding
import app.nexd.android.di.sharedGraphViewModel
import app.nexd.android.ui.common.DefaultSnackbar
import app.nexd.android.ui.common.helprequest.AdditionalInfoBinder
import app.nexd.android.ui.common.helprequest.HelpRequestCreateArticleBinder
import app.nexd.android.ui.common.helprequest.HelpRequestCreateArticleBinder.ArticleViewModel
import app.nexd.android.ui.seeker.create.SeekerCreateRequestViewModel
import app.nexd.android.ui.seeker.create.SeekerCreateRequestViewModel.Progress.*
import app.nexd.android.ui.utils.extensions.dpToPx
import app.nexd.android.ui.utils.extensions.observe
import com.google.android.material.snackbar.Snackbar
import mva2.adapter.ItemSection
import mva2.adapter.ListSection
import mva2.adapter.MultiViewAdapter
import mva2.adapter.util.Mode

class SeekerCreateRequestEnterArticlesFragment : Fragment() {

    private val vm: SeekerCreateRequestViewModel by sharedGraphViewModel(R.id.nav_seeker_create_request)

    private lateinit var binding: FragmentSeekerCreateRequestEnterArticlesBinding

    private val adapter = MultiViewAdapter()
    private val articlesSection: ListSection<ArticleViewModel> = ListSection()
    private val additionalInfoSection: ItemSection<String> = ItemSection()
    private val confirmationSection: ItemSection<Unit> = ItemSection()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSeekerCreateRequestEnterArticlesBinding.inflate(inflater, container, false)
        binding.viewModel = vm
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initRecyclerView()
        setVmObserver()
    }

    private fun initRecyclerView() {
        binding.recyclerViewRequests.adapter = adapter

        adapter.registerItemBinders(
            HelpRequestCreateArticleBinder(viewLifecycleOwner),
            AdditionalInfoBinder()
        )
        adapter.setExpansionMode(Mode.MULTIPLE)

        adapter.addSection(articlesSection)

        adapter.addSection(additionalInfoSection)

        binding.recyclerViewRequests.layoutManager = LinearLayoutManager(context)

        // add scrollable padding on top and bottom of the recycler view
        binding.recyclerViewRequests.addItemDecoration(object : RecyclerView.ItemDecoration() {

            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                super.getItemOffsets(outRect, view, parent, state)

                if (parent.getChildLayoutPosition(view) == 0) {
                    outRect.top = 10.dpToPx
                }
                outRect.bottom = 10.dpToPx
            }

        })
    }

    private fun setVmObserver() {
        vm.inputs.observe(viewLifecycleOwner, Observer(articlesSection::set))

        vm.additionalInformation.observe(viewLifecycleOwner) {
            it?.let {
                additionalInfoSection.setItem(it)
            }
        }

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
                        DefaultSnackbar(binding.root, errorMessageId, Snackbar.LENGTH_SHORT)
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
