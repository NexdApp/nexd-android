package app.nexd.android.ui.common.helprequest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import app.nexd.android.api.model.Unit
import app.nexd.android.databinding.ViewChipArticleUnitBinding
import mva2.extension.DBItemBinder
import kotlin.reflect.KFunction0

class UnitViewBinder(
    private val lifecycleOwner: LifecycleOwner,
    private val selectedUnit: MutableLiveData<Unit>,
    private val toggleVisibilityFunction: KFunction0<kotlin.Unit>
) : DBItemBinder<Unit, ViewChipArticleUnitBinding>() {

    override fun canBindData(item: Any?): Boolean {
        return item is Unit
    }

    override fun createBinding(parent: ViewGroup?): ViewChipArticleUnitBinding {
        val inflater = LayoutInflater.from(parent?.context)
        return ViewChipArticleUnitBinding.inflate(inflater, parent, false).apply {
            this.lifecycleOwner = this@UnitViewBinder.lifecycleOwner
        }
    }

    override fun bindModel(unit: Unit, binding: ViewChipArticleUnitBinding) {
        binding.unit = unit
        binding.selectedUnit = selectedUnit

        binding.root.setOnClickListener {
            selectedUnit.value = unit
            toggleVisibilityFunction()
        }

        binding.articleUnitName.isSelected = true
    }

}