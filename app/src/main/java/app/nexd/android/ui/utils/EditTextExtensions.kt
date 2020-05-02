package app.nexd.android.ui.utils

import android.view.inputmethod.EditorInfo
import android.widget.EditText

fun EditText.setDoneListener(action: () -> Unit) {
    setOnEditorActionListener { _, i, _ ->
        if (i == EditorInfo.IME_ACTION_DONE) {
            action()
        }
        false
    }

}