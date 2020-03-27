package app.nexd.android.ui.view

import android.content.Context
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import app.nexd.android.R
import app.nexd.android.api.model.RequestArticle
import app.nexd.android.api.model.RequestEntity
import app.nexd.android.ui.buyer.CheckoutItemBinder
import kotlinx.android.synthetic.main.view_checkout_request.view.*
import mva2.adapter.ListSection
import mva2.adapter.MultiViewAdapter

class CheckoutRequestView constructor(context: Context, var request: RequestEntity)
    : LinearLayout(context) {

    private var adapter = MultiViewAdapter()

    init {
        inflate(context, R.layout.view_checkout_request, this)
        title.text = request.requester?.lastName

        items.adapter = adapter
        items.layoutManager = LinearLayoutManager(context)

        adapter.registerItemBinders(
            CheckoutItemBinder()
        )

        val list = ListSection<RequestArticle>()
        list.addAll(request.articles)
        adapter.addSection(list)

    }

}