package app.nexd.android.ui.common.helprequest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager
import app.nexd.android.R
import app.nexd.android.api.model.Article
import app.nexd.android.api.model.Unit
import app.nexd.android.databinding.RowNewHelpRequestArticleBinding
import app.nexd.android.ui.common.helprequest.HelpRequestCreateArticleBinder.ArticleViewModel
import app.nexd.android.ui.utils.extensions.map
import mva2.adapter.ListSection
import mva2.adapter.MultiViewAdapter
import mva2.extension.DBItemBinder

internal typealias ArticleViewModelBinder = DBItemBinder<ArticleViewModel, RowNewHelpRequestArticleBinding>

class HelpRequestCreateArticleBinder(private val lifecycleOwner: LifecycleOwner) : ArticleViewModelBinder() {

    data class ArticleViewModel(
        val article: MutableLiveData<Article?>,
        val articleName: MutableLiveData<String>,
        val amount: MutableLiveData<String>,
        val units: LiveData<List<Unit>>,
        val selectedUnit: MutableLiveData<Unit>,
        val isNew: MutableLiveData<Boolean>,
        val listener: ActionListener
    ) {

        interface ActionListener {
            fun onConfirm(viewModel: ArticleViewModel)
        }

        val buttonsVisibility = MediatorLiveData<Int>().apply {
            addSource(isNew) { value = if (it) View.VISIBLE else View.GONE }
        }

        private val unitsVisible = MutableLiveData<Boolean>(false)

        val unitsVisibility: LiveData<Int> = unitsVisible.map {
            if (it) View.VISIBLE else View.GONE
        }

        val unitButtonBg = unitsVisible.map {
            if (it) R.drawable.rounded_top_solid else R.drawable.rounded_white
        }

        fun toggleUnitsVisibility() {
            unitsVisible.value = unitsVisible.value?.not()
        }
    }


    override fun canBindData(item: Any?): Boolean {
        return item is ArticleViewModel
    }

    override fun createBinding(parent: ViewGroup): RowNewHelpRequestArticleBinding {
        val inflater = LayoutInflater.from(parent.context)
        return RowNewHelpRequestArticleBinding.inflate(inflater, parent, false).apply {

            recyclerviewNewArticleUnits.apply {
                adapter = MultiViewAdapter()
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            }

            lifecycleOwner = this@HelpRequestCreateArticleBinder.lifecycleOwner
        }
    }

    override fun bindModel(viewModel: ArticleViewModel, binding: RowNewHelpRequestArticleBinding) {
        binding.viewModel = viewModel

        val adapter = binding.recyclerviewNewArticleUnits.adapter as MultiViewAdapter

        adapter.removeAllSections()
        adapter.registerItemBinders(
            UnitViewBinder(
                lifecycleOwner,
                viewModel.selectedUnit))

        with(ListSection<Unit>()) {
            adapter.addSection(this)
            viewModel.units.observe(lifecycleOwner, Observer(this::set))
        }
    }


}