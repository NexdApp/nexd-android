package app.nexd.android.ui.seeker.create

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import app.nexd.android.databinding.FragmentSeekerCreateRequestBinding
import app.nexd.android.ui.common.DefaultSnackbar
import app.nexd.android.ui.common.HelpRequestCreateArticleBinder
import app.nexd.android.ui.seeker.create.SeekerCreateRequestViewModel.Progress.*
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_seeker_create_request.*
import mva2.adapter.ListSection
import mva2.adapter.MultiViewAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class SeekerCreateRequestFragment : Fragment() {

    private val vm: SeekerCreateRequestViewModel by viewModel()

    private lateinit var binding: FragmentSeekerCreateRequestBinding

    private val adapter = MultiViewAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSeekerCreateRequestBinding.inflate(inflater, container, false)
        binding.viewModel = vm
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView_requests.layoutManager = LinearLayoutManager(context)
        recyclerView_requests.adapter = adapter

        adapter.registerItemBinders(HelpRequestCreateArticleBinder())

        vm.articles.observe(viewLifecycleOwner, Observer {
            adapter.removeAllSections()
            val articlesSection = ListSection<HelpRequestCreateArticleBinder.ArticleInput>()
            articlesSection.addAll(it)
            adapter.addSection(articlesSection)
        })

        button_abort.setOnClickListener {
            findNavController().navigateUp()
        }

        vm.progress.observe(viewLifecycleOwner, Observer { progress ->
            when (progress) {
                is Idle -> {
                }
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
