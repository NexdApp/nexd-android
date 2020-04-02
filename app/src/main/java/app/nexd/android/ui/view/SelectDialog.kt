package app.nexd.android.ui.view

import android.app.Activity
import android.app.AlertDialog
import android.text.method.LinkMovementMethod
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import app.nexd.android.R


class SelectDialog @JvmOverloads
constructor(context: Activity, caption: String = context.getString(R.string.input), details: CharSequence? = null,
            layout: ViewGroup? = null): AlertDialog.Builder(context) {

    var details: String? = null
        set(value) {
            if (value != null) {
                mDetails.visibility = View.VISIBLE
                mDetails.text = details
            } else
                mDetails.visibility = View.GONE
            field = value
        }

    private var mDetails: TextView
    private var mConfirm: Button
    private var mCancel: Button

    private var alertDialog: AlertDialog? = null

    init {
        val view = context.layoutInflater.inflate(R.layout.dialog_select, null)
        setView(view)

        mDetails = view.findViewById(R.id.details)
        mConfirm = view.findViewById(R.id.confirm)
        mCancel = view.findViewById(R.id.dismiss)
        mDetails.movementMethod = LinkMovementMethod.getInstance() //for links

        if (details != null) {
            mDetails.visibility = View.VISIBLE
            mDetails.text = details
        } else
            mDetails.visibility = View.GONE

        setTitle(caption)
        setConfirmButton()
        setNegativeButton()
    }

    fun dismiss() {
        alertDialog?.dismiss()
    }

    override fun setTitle(title: CharSequence?): SelectDialog {
        return super.setTitle(title) as SelectDialog
    }

    fun setConfirmButton(label: String = context.getString(R.string.ok), listener: (() -> Unit)? = null): SelectDialog {
        mConfirm.text = label
        mConfirm.setOnClickListener {
            mConfirm.isEnabled = false
            listener?.invoke()
            dismiss()
        }
        return this
    }

    @JvmOverloads
    fun setNegativeButton(label: String = context.getString(R.string.abort), listener: (() -> Unit)? = null): SelectDialog {
        mCancel.visibility = View.VISIBLE
        mCancel.text = label
        mCancel.setOnClickListener {
            mCancel.isEnabled = false
            listener?.invoke()
            dismiss()
        }
        return this
    }

    override fun show(): AlertDialog {
        alertDialog = super.show()
        alertDialog?.window?.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.dialog_background))
        return alertDialog!!
    }
}
