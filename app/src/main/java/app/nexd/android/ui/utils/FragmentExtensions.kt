package app.nexd.android.ui.utils

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import org.koin.android.ext.android.getKoin
import org.koin.androidx.viewmodel.koin.getViewModel
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.Qualifier

/**
 * This extension allows creation of a shared ViewModel bound to the specified nested graph.
 *
 * @see <a href="https://github.com/InsertKoinIO/koin/issues/442#issuecomment-585748864">https://github.com/InsertKoinIO/koin/issues/442#issuecomment-585748864</a>
 */
inline fun <reified VM : ViewModel> Fragment.sharedGraphViewModel(
    @IdRes navGraphId: Int,
    qualifier: Qualifier? = null,
    noinline parameters: ParametersDefinition? = null
) = lazy {
    val owner = findNavController().getViewModelStoreOwner(navGraphId)
    getKoin().getViewModel(owner, VM::class, qualifier, parameters)
}