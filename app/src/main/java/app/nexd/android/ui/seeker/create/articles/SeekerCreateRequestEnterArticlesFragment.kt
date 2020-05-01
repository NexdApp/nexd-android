package app.nexd.android.ui.seeker.create.articles

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import app.nexd.android.R
import app.nexd.android.api.model.CreateHelpRequestArticleDto
import app.nexd.android.api.model.HelpRequestCreateDto
import app.nexd.android.di.sharedGraphViewModel
import app.nexd.android.ui.common.HelpRequestCreateArticleBinder
import app.nexd.android.ui.seeker.create.SeekerCreateRequestViewModel
import kotlinx.android.synthetic.main.fragment_seeker_create_request_enter_articles.*
import mva2.adapter.ListSection
import mva2.adapter.MultiViewAdapter

class SeekerCreateRequestEnterArticlesFragment : Fragment() {

    private val vm: SeekerCreateRequestViewModel by sharedGraphViewModel(R.id.nav_seeker_create_request)

    private lateinit var adapter: MultiViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_seeker_create_request_enter_articles,
            container,
            false
        )
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
                val articlesInput = articles.map { HelpRequestCreateArticleBinder.ArticleInput(it.id, MutableLiveData(it.name), MutableLiveData(0L.toString())) }
                articlesSection.addAll(articlesInput)

                adapter.addSection(articlesSection)

                button_accept.setOnClickListener {

                    val request = HelpRequestCreateDto()
                        .articles(articlesInput
                            .filter { it.amount.value?.toLong() ?: 0L > 0L }
                            .map {
                                CreateHelpRequestArticleDto()
                                    .articleCount(it.amount.value!!.toLong())
                                    .articleId(it.articleId)

                            })
                        .firstName(currentUser.firstName)
                        .lastName(currentUser.lastName)
                        .street(currentUser.street)
                        .number(currentUser.number)
                        .zipCode(currentUser.zipCode)
                        .city(currentUser.city)
                        .phoneNumber(currentUser.phoneNumber)
                        .additionalRequest(textView_additionalRequest.text.toString())

                    if (request.articles.isNullOrEmpty()) {
                        Toast.makeText(
                            requireContext(),
                            getString(R.string.seeker_request_create_no_articles),
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        vm.setRequestToConfirm(request)
                    }
                }
            })
        })

        vm.state().observe(viewLifecycleOwner, Observer {
            when (it) {
                SeekerCreateRequestViewModel.State.PROCESSING -> {
                    findNavController().navigate(R.id.action_seekerCreateRequestEnterArticlesFragment_to_seekerCreateRequestConfirmAddressFragment)
                }
                else -> Log.d(
                    SeekerCreateRequestEnterArticlesFragment::class.simpleName,
                    "unhandled state $it"
                )
            }

        })
    }
}
