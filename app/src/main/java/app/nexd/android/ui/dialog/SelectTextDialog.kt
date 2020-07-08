package app.nexd.android.ui.dialog

import android.app.AlertDialog
import android.content.Context
import android.text.InputType
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import app.nexd.android.R

class SelectTextDialog @JvmOverloads constructor(
    context: Context, caption: String = "", value: String = "", details: CharSequence = ""
) : SelectDialog(context, caption, details) {

    private var mText: EditText
    private var mLabel: TextView

    var value: String = ""
        set(value) {
            mText.setText(value)
            if (mText.hasFocus())
                mText.setSelection(mText.length())
            field = value
        }
        get() = mText.text.trim().toString()

    init {
        val view = View.inflate(context, R.layout.dialog_select_text, null)
        mText = view.findViewById(R.id.dialog_select_text_input)
        mLabel = view.findViewById(R.id.dialog_select_text_label)

        setContainer(view)

        mText.setOnEditorActionListener { _, i, _ ->
            if (i == EditorInfo.IME_ACTION_DONE)
                confirm()
            false
        }

        this.value = value
        setLabel()
    }

    @JvmOverloads
    fun setLabel(
        inputType: Int = InputType.TYPE_CLASS_TEXT,
        label: String? = null
    ): SelectTextDialog {
        mText.inputType = inputType

        if (label.isNullOrEmpty()) {
            mLabel.visibility = View.GONE
            mText.hint = context.getString(R.string.select_text_dialog_text_hint_default)
        } else {
            mLabel.text = label
            mLabel.visibility = View.VISIBLE
            mText.hint = null
        }
        return this
    }

    override fun getData(): Any {
        return if (mText.inputType == InputType.TYPE_CLASS_NUMBER) value.toInt() else value
    }

    override fun show(): AlertDialog {
        mText.requestFocus()
        showKeyboard()
        return super.show()
    }
}