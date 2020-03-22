package de.andrestefanov.android.nearbuy.ui.view

import android.content.Context
import android.view.View
import android.widget.LinearLayout
import de.andrestefanov.android.nearbuy.R
import de.andrestefanov.android.nearbuy.api.data.HelpRequest
import kotlinx.android.synthetic.main.view_request_delivery.view.*

class RequestDeliveryView(context: Context, var request: HelpRequest): LinearLayout(context) {

    init {
        inflate(context, R.layout.view_request_delivery, this)

        name.text = request.name
        address.text = "Adresse:\n${request.location}"
        phonenumber.text = "Tel. Nr.. 1552343325434"// "Tel. Nr.: ${request.phonenumber}" TODO add when model updated
    }

}