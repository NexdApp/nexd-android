package app.nexd.android.ui.helper.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import app.nexd.android.R
import app.nexd.android.api.model.HelpRequestArticle
import kotlinx.android.synthetic.main.fragment_helper_request_detail.*
import mva2.adapter.ListSection
import mva2.adapter.MultiViewAdapter


class HelperDetailFragment : Fragment() {

    private val args: HelperDetailFragmentArgs by navArgs()

    private lateinit var viewModel: HelperDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(HelperDetailViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_helper_request_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val adapter = MultiViewAdapter()
        recyclerView_requests.adapter = adapter
        recyclerView_requests.layoutManager = LinearLayoutManager(context)

        adapter.registerItemBinders(
            HelpRequestArticleBinder()
        )

        viewModel.requestDetails(args.requestId.toLong()).observe(viewLifecycleOwner, Observer { request ->
            adapter.removeAllSections()

            name.text = context!!.getString(
                R.string.helper_request_detail_name_layout,
                request.requester!!.firstName
                , request.requester!!.lastName
            )

            val list = ListSection<HelpRequestArticle>()
            list.addAll(request.articles!!)
            adapter.addSection(list)

            setAccepted(request.helpListId != null)

            accept.setOnClickListener {
                request.id?.let {
                    viewModel.acceptRequest(it)
                    findNavController().popBackStack()
                }
            }
        })
    }

    private fun setAccepted(accepted: Boolean) {
        accept.text =
            getString(if (accepted) R.string.helper_request_detail_button_accepted else R.string.helper_request_detail_button_accept)
        accept.isEnabled = !accepted
    }

}
