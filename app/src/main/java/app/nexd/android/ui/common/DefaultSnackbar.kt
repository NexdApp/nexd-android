package app.nexd.android.ui.common

import android.graphics.Typeface
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import app.nexd.android.R
import com.google.android.material.snackbar.Snackbar

class DefaultSnackbar(parent: View, text: CharSequence, duration: Int) {

    constructor (parent: View, @StringRes text: Int, duration: Int)
            : this(parent, parent.resources.getString(text), duration)

    init {
        val snackBar = Snackbar.make(parent, text, duration)
        val snackBarView = snackBar.view
        val params =  snackBarView.layoutParams as CoordinatorLayout.LayoutParams
        val margin = 28

        params.setMargins(params.leftMargin + margin,
            params.topMargin,
            params.rightMargin + margin,
            params.bottomMargin + margin)

        snackBarView.layoutParams = params
        snackBarView.setPadding(0, 0, 0 , 0)
        snackBarView.background = ContextCompat.getDrawable(parent.context,
            R.drawable.rounded_button_white_semi_translucent)

        val textView: TextView = snackBarView.findViewById(
            com.google.android.material.R.id.snackbar_text)
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22f)
        textView.setTextColor(ContextCompat.getColor(parent.context, R.color.colorPrimaryDark))

        textView.setTypeface(null, Typeface.BOLD)
        snackBar.show()
    }

}