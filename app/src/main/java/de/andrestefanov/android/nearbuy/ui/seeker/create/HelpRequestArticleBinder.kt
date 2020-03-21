package de.andrestefanov.android.nearbuy.ui.seeker.create

import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import de.andrestefanov.android.nearbuy.R
import de.andrestefanov.android.nearbuy.api.data.Article
import mva2.adapter.ItemBinder
import mva2.adapter.ItemViewHolder

class HelpRequestArticleBinder : ItemBinder<Article, HelpRequestArticleBinder.ViewHolder>() {

    class ViewHolder(itemView: View) : ItemViewHolder<Article>(itemView) {
        var title: TextView = itemView.findViewById(R.id.request_list_item_title)

        init {
            itemView.findViewById<CheckBox>(R.id.request_list_item_checkbox).visibility = View.GONE
        }
    }

    override fun bindViewHolder(holder: ViewHolder?, item: Article?) {
        TODO("Not yet implemented")
    }

    override fun createViewHolder(parent: ViewGroup?): ViewHolder {
        TODO("Not yet implemented")
    }

    override fun canBindData(item: Any?): Boolean {
        TODO("Not yet implemented")
    }


}