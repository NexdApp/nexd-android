package de.andrestefanov.android.nearbuy.ui.view

import android.content.Context
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import de.andrestefanov.android.nearbuy.R
import de.andrestefanov.android.nearbuy.api.data.HelpRequest
import de.andrestefanov.android.nearbuy.api.data.HelpRequestItem
import de.andrestefanov.android.nearbuy.api.data.ShoppingList
import de.andrestefanov.android.nearbuy.ui.buyer.CheckoutItemBinder
import de.andrestefanov.android.nearbuy.ui.buyer.ShoppingListEntryBinder
import kotlinx.android.synthetic.main.view_checkout_request.view.*
import mva2.adapter.ListSection
import mva2.adapter.MultiViewAdapter

class CheckoutRequestView constructor(context: Context, var request: HelpRequest)
    : LinearLayout(context) {

    private var adapter = MultiViewAdapter()

    init {
        inflate(context, R.layout.view_checkout_request, this)
        title.text = request.name

        items.adapter = adapter
        items.layoutManager = LinearLayoutManager(context)

        adapter.registerItemBinders(
            CheckoutItemBinder()
        )

        val list = ListSection<HelpRequestItem>()
        list.addAll(request.items)
        adapter.addSection(list)

    }

}