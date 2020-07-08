package app.nexd.android.ui.utils.extensions

import app.nexd.android.api.model.Unit

fun Unit.name(quantity: Int): String {
    return when(quantity) {
        0 -> nameZero
        1 -> nameOne
        2 -> nameTwo
        else -> nameMany
    }
}