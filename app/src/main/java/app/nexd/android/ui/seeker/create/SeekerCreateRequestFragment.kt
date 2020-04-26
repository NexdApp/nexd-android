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
import app.nexd.android.R
import app.nexd.android.api.model.CreateHelpRequestArticleDto
import app.nexd.android.api.model.HelpRequestCreateDto
import app.nexd.android.ui.common.HelpRequestCreateArticleBinder
import kotlinx.android.synthetic.main.fragment_seeker_create_request.*
import mva2.adapter.ListSection
import mva2.adapter.MultiViewAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class SeekerCreateRequestFragment : Fragment() {

    private val vm: SeekerCreateRequestViewModel by viewModel()

    private lateinit var adapter: MultiViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_seeker_create_request, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        recyclerView_requests.layoutManager = LinearLayoutManager(context)

        adapter = MultiViewAdapter()
        recyclerView_requests.adapter = adapter

        adapter.registerItemBinders(HelpRequestCreateArticleBinder())

        vm.getCurrentUser().observe(viewLifecycleOwner, Observer { currentUser ->

            vm.getArticles().observe(viewLifecycleOwner, Observer { articles ->
                adapter.removeAllSections()

                val articlesSection = ListSection<HelpRequestCreateArticleBinder.ArticleInput>()
                val articlesInput = articles.map { HelpRequestCreateArticleBinder.ArticleInput(it) }
                articlesSection.addAll(articlesInput)

                adapter.addSection(articlesSection)

                button_accept.setOnClickListener {
                    val request = HelpRequestCreateDto()
                        .articles(articlesInput
                            .filter { it.amount > 0 }
                            .map {
                                CreateHelpRequestArticleDto()
                                    .articleCount(it.amount)
                                    .articleId(it.article.id)

                            })
                        .street(currentUser.street)
                        .number(currentUser.number)
                        .zipCode(currentUser.zipCode)
                        .city(currentUser.city)
                        .phoneNumber(currentUser.phoneNumber)
                        .additionalRequest(textView_additionalRequest.text.toString())

                    vm.sendRequest(request)
                }
            })

        })

        vm.state().observe(viewLifecycleOwner, Observer {
            when (it) {
                // TODO: handle all states
                SeekerCreateRequestViewModel.State.FINISHED -> findNavController().popBackStack()
                else -> Log.d(SeekerCreateRequestFragment::class.simpleName, "unhandled state $it")
            }
        })
    }

}
