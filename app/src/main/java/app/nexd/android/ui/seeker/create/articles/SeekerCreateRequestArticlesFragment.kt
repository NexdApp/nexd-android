package app.nexd.android.ui.seeker.create.articles

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import app.nexd.android.R
import app.nexd.android.databinding.FragmentSeekerCreateRequestEnterArticlesBinding
import app.nexd.android.di.sharedGraphViewModel
import app.nexd.android.ui.seeker.create.SeekerCreateRequestViewModel
import app.nexd.android.ui.utils.livedata.observe
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import kotlinx.android.synthetic.main.fragment_seeker_create_request_enter_articles.*

class SeekerCreateRequestArticlesFragment : Fragment() {

    private lateinit var binding: FragmentSeekerCreateRequestEnterArticlesBinding

    private lateinit var unitsAdapter : ArticleUnitsAdapter

    private val vm: SeekerCreateRequestViewModel by sharedGraphViewModel(R.id.nav_seeker_create_request)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSeekerCreateRequestEnterArticlesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewModel = vm
        binding.lifecycleOwner = viewLifecycleOwner

        val layoutManager = FlexboxLayoutManager(context).apply {
            flexDirection = FlexDirection.ROW
            flexWrap = FlexWrap.WRAP
            justifyContent = JustifyContent.FLEX_START
        }

        unitsAdapter = ArticleUnitsAdapter()
        vm.getUnits().observe(viewLifecycleOwner, unitsAdapter::setData)

        recyclerview_new_article_units.layoutManager = layoutManager
        recyclerview_new_article_units.adapter = unitsAdapter
    }

}