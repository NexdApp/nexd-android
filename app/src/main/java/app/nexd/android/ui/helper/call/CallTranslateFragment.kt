package app.nexd.android.ui.helper.call

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import app.nexd.android.R
import app.nexd.android.ui.seeker.create.HelpRequestArticleBinder
import kotlinx.android.synthetic.main.fragment_translate_call.*
import mva2.adapter.ListSection
import mva2.adapter.MultiViewAdapter

class CallTranslateFragment: Fragment() {

    private val viewModel: CallTranslateViewModel by viewModels()

    private val adapter = MultiViewAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_translate_call, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView_requests.adapter = adapter
        recyclerView_requests.layoutManager = LinearLayoutManager(context)
        adapter.registerItemBinders(
            HelpRequestArticleBinder()
        )

        viewModel.getArticles().observe(viewLifecycleOwner, Observer { articles ->
            val articlesSection = ListSection<HelpRequestArticleBinder.ArticleInput>()
            val articlesInput = articles.map { HelpRequestArticleBinder.ArticleInput(it) }
            articlesSection.addAll(articlesInput)

            adapter.addSection(articlesSection)
        })
    }

}