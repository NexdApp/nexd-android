package app.nexd.android.ui.dialog

import android.content.Context

class SelectBasicDialog(context: Context?, caption: String, details: CharSequence? = null):
    SelectDialog(context, caption, details) {

    override fun getData(): Any {
        return Unit
    }

}