package app.nexd.android.ui.auth.register.model

import androidx.annotation.StringRes

sealed class Effect {
    object NavigateToRoleView: Effect()
    data class ShowErrorMessage(@StringRes val message: Int): Effect()
}