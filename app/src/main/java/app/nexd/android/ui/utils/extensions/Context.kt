package app.nexd.android.ui.utils.extensions

import android.content.Context
import app.nexd.android.R
import app.nexd.android.api.model.AvailableLanguages

fun Context.currentLanguage() : AvailableLanguages {
    return AvailableLanguages.fromValue(this.getString(R.string.language))
}