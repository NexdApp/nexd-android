package de.andrestefanov.android.nearbuy.ui.seeker.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import de.andrestefanov.android.nearbuy.R
import de.andrestefanov.android.nearbuy.api.data.Article
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

        recyclerView.layoutManager = LinearLayoutManager(context)

        val adapter = MultiViewAdapter()
        recyclerView.adapter = adapter

        adapter.registerItemBinders(HelpRequestArticleBinder())

        viewModel.getArticles().observe(viewLifecycleOwner, Observer { articles ->
            adapter.removeAllSections()

            val articlesSection = ListSection<Article>()
            articlesSection.addAll(articles)

            adapter.addSection(articlesSection)
        })
    }

}
