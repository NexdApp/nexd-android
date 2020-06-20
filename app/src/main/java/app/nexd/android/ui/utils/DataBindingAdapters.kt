package app.nexd.android.ui.utils

import android.graphics.Typeface
import android.widget.Button
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.databinding.BindingAdapter

object DataBindingAdapters {

    @BindingAdapter("android:error")
    @JvmStatic
    fun textViewBindError(view: TextView, @StringRes error: Int) {
        view.error = if (error != 0) view.context.getString(error) else null
    }

    @BindingAdapter("android:text")
    @JvmStatic
    fun textViewBindText(view: TextView, @StringRes text: Int) {
        view.text = if (text != 0) view.context.getString(text) else null
    }

    @JvmStatic
    @BindingAdapter("android:textStyle")
    fun setTextStyle(view: Button, style: String) {
        when (style) {
            "bold" -> view.setTypeface(null, Typeface.BOLD)
            "italic" -> view.setTypeface(null, Typeface.ITALIC)
            "bold_italic" -> view.setTypeface(null, Typeface.BOLD_ITALIC)
            else -> view.setTypeface(null, Typeface.NORMAL)
        }
    }
}