package de.andrestefanov.android.nearbuy.ui.requester

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import de.andrestefanov.android.nearbuy.R

class CreatedRequestsAdapter : RecyclerView.Adapter<CreatedRequestsAdapter.RequestItemViewHolder>() {

    class RequestItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RequestItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.request_list_item_view, parent, false)
        return RequestItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 5
    }

    override fun onBindViewHolder(holder: RequestItemViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

}