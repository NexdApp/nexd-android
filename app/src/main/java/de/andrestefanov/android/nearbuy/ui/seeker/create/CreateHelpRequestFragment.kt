package de.andrestefanov.android.nearbuy.ui.seeker.create

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import de.andrestefanov.android.nearbuy.R
import de.andrestefanov.android.nearbuy.api.data.Article
import io.reactivex.plugins.RxJavaPlugins
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

        viewModel.getArticles().observe(viewLifecycleOwner, Observer { request ->
            adapter.removeAllSections()

            val articlesSection = ListSection<Article>()
            articlesSection.addAll(request.articles)

            adapter.addSection(articlesSection)

            button_accept.setOnClickListener {
                request.additionalRequest = editText_additionalRequest.text.toString()
                viewModel.sendRequest(request)
            }

            editText_additionalRequest.setText(request.additionalRequest, TextView.BufferType.EDITABLE)
        })
    }

}
