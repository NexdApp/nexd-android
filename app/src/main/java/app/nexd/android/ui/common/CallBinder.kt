package app.nexd.android.ui.common

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import app.nexd.android.R
import app.nexd.android.api.model.Call
import app.nexd.android.ui.common.Constants.Companion.DATE_FORMAT
import kotlinx.android.synthetic.main.row_call.view.*
import mva2.adapter.ItemBinder
import mva2.adapter.ItemViewHolder
import java.text.SimpleDateFormat
import java.util.*

class CallBinder: ItemBinder<Call, CallBinder.ViewHolder>() {

    class ViewHolder(itemView: View) : ItemViewHolder<Call>(itemView) {
        private val title: TextView = itemView.textView_title

        init {
            itemView.setOnClickListener {
                toggleItemSelection()
            }
        }

        fun bind(item: Call) {
            title.text = SimpleDateFormat(DATE_FORMAT, Locale.getDefault()).format(item.created)
        }
    }

    override fun bindViewHolder(holder: ViewHolder, item: Call) {
        holder.bind(item)
    }

    override fun createViewHolder(parent: ViewGroup): ViewHolder {
        return ViewHolder(
            inflate(
                parent,
                R.layout.row_call
            )
        )
    }

    override fun canBindData(item: Any): Boolean {
        return item is Call
    }


}