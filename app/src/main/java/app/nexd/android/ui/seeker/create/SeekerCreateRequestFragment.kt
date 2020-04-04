package app.nexd.android.ui.seeker.create

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import app.nexd.android.R
import app.nexd.android.api.model.CreateHelpRequestArticleDto
import app.nexd.android.api.model.HelpRequestCreateDto
import kotlinx.android.synthetic.main.fragment_seeker_create_request.*
import mva2.adapter.ListSection
import mva2.adapter.MultiViewAdapter

class SeekerCreateRequestFragment : Fragment() {

    private lateinit var viewModel: CreateHelpRequestViewModel

    private lateinit var adapter: MultiViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_seeker_create_request, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CreateHelpRequestViewModel::class.java)

        recyclerView_articles.layoutManager = LinearLayoutManager(context)

        adapter = MultiViewAdapter()
        recyclerView_articles.adapter = adapter

        adapter.registerItemBinders(HelpRequestArticleBinder())

        viewModel.getArticles().observe(viewLifecycleOwner, Observer { articles ->
            adapter.removeAllSections()

            val articlesSection = ListSection<HelpRequestArticleBinder.ArticleInput>()
            val articlesInput = articles.map { HelpRequestArticleBinder.ArticleInput(it) }
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
                    .additionalRequest(editText_additionalRequest.text.toString())

                viewModel.sendRequest(request)
            }
        })

        viewModel.state().observe(viewLifecycleOwner, Observer {
            when (it) {
                // TODO: handle all states
                CreateHelpRequestViewModel.State.FINISHED -> findNavController().popBackStack()
                else -> Log.d(SeekerCreateRequestFragment::class.simpleName, "unhandled state $it")
            }
        })
    }

}
