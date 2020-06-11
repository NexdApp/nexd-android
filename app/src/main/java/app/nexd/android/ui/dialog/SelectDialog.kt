package app.nexd.android.ui.dialog

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.text.method.LinkMovementMethod
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import app.nexd.android.R
import kotlinx.android.synthetic.main.dialog_select.view.*


abstract class SelectDialog @JvmOverloads constructor(
    context: Context,
    caption: String,
    details: CharSequence? = null,
    layout: ViewGroup? = null
) : AlertDialog.Builder(context) {

    var caption: String = ""
        set(value) {
            mTitle.text = value
            field = value
        }

    var details: String? = null
        set(value) {
            if (value != null) {
                mDetails.visibility = View.VISIBLE
                mDetails.text = details
            } else
                mDetails.visibility = View.GONE
            field = value
        }

    private var mTitle: TextView
    private var mDetails: TextView
    private var mContainer: LinearLayout
    private var mConfirm: Button
    private var mDismiss: Button

    private var alertDialog: AlertDialog? = null

    private var confirmListener: ((Any) -> Unit)? = null
    private var negativeListener: (() -> Unit)? = null
    private var dismissListener: DialogInterface.OnDismissListener? = null

    init {
        val view = View.inflate(context, R.layout.dialog_select, null)
        this.setView(view)

        mTitle = view.textView_title
        mDetails = view.textView_details
        mContainer = view.linearLayout_container
        mConfirm = view.button_confirm
        mDismiss = view.button_dismiss

        mConfirm.setOnClickListener {
            confirm()
        }
        mDismiss.setOnClickListener {
            negativeConfirm()
        }
        mDetails.movementMethod = LinkMovementMethod.getInstance() //for links

        if (layout != null)
            setContainer(layout)

        if (details != null) {
            mDetails.visibility = View.VISIBLE
            mDetails.text = details
        } else
            mDetails.visibility = View.GONE


        this.caption = caption
    }

    fun dismiss() {
        alertDialog?.dismiss()
    }

    protected fun setContainer(view: View) {
        mContainer.removeAllViews()
        mContainer.addView(view)
    }

    protected fun showKeyboard() {
        val inputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }

    protected fun closeKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (!imm.isActive)
            imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
    }

    fun setConfirmButton(
        label: String = context.getString(R.string.dialog_button_confirm),
        listener: ((Any) -> Unit)? = null
    ): SelectDialog {
        mConfirm.text = label
        this.confirmListener = listener
        return this
    }

    @JvmOverloads
    fun setNegativeButton(
        label: String = context.getString(R.string.dialog_button_abort),
        listener: (() -> Unit)? = null
    ): SelectDialog {
        mDismiss.visibility = View.VISIBLE
        mDismiss.text = label
        this.negativeListener = listener
        return this
    }

    override fun setOnDismissListener(onDismissListener: DialogInterface.OnDismissListener?): SelectDialog {
        this.dismissListener = onDismissListener
        return this
    }

    override fun show(): AlertDialog {
        alertDialog = super.show()
        alertDialog?.setOnDismissListener {
            closeKeyboard()
            dismissListener?.onDismiss(it)
        }
        alertDialog?.window?.setBackgroundDrawable(null)
        return alertDialog!!
    }

    protected fun confirm() {
        confirmListener?.invoke(getData())
        dismiss()
    }

    protected fun negativeConfirm() {
        negativeListener?.invoke()
        dismiss()
    }

    abstract fun getData(): Any
}
