package de.andrestefanov.android.nearbuy.ui.seeker.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import de.andrestefanov.android.nearbuy.R
import de.andrestefanov.android.nearbuy.api.model.Article
import de.andrestefanov.android.nearbuy.api.model.CreateRequestArticleDto
import de.andrestefanov.android.nearbuy.api.model.RequestFormDto
import kotlinx.android.synthetic.main.create_help_request_fragment.*
import mva2.adapter.ListSection
import mva2.adapter.MultiViewAdapter

class CreateHelpRequestFragment : Fragment() {

    private lateinit var viewModel: CreateHelpRequestViewModel

    private lateinit var adapter: MultiViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.create_help_request_fragment, container, false)
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

                val request = RequestFormDto()
                    .articles(articlesInput.map {
                        CreateRequestArticleDto()
                            .articleCount(it.amount)
                            .articleId(it.article.id)
                    })
                    .additionalRequest(editText_additionalRequest.text.toString())

                viewModel.sendRequest(request)
            }
        })
    }

}
