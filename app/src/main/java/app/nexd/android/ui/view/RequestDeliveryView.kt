package app.nexd.android.ui.view

import android.content.Context
import android.widget.LinearLayout
import app.nexd.android.R
import app.nexd.android.api.model.HelpRequest
import kotlinx.android.synthetic.main.view_request_delivery.view.*

class RequestDeliveryView(context: Context, var request: HelpRequest): LinearLayout(context) {

    init {
        inflate(context, R.layout.view_request_delivery, this)

        name.text = request.requester?.lastName
        address.text = "%1 %2 %3".format(context.getString(R.string.delivery_request_address),
            request.street,
            request.number)
        phonenumber.text = "%1 %2".format(context.getString(R.string.delivery_request_phoneNumber),
            request.phoneNumber)
    }

}