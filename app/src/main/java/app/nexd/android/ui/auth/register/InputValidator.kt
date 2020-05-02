package app.nexd.android.ui.auth.register

import app.nexd.android.ui.auth.register.model.Event
import app.nexd.android.ui.auth.register.model.ValidationError

class InputValidator() {

    fun validate(
        event: Event.UserRegistrationEvent
    ): List<ValidationError> {
        val errors = mutableListOf<ValidationError>()
        if (event.locality.isEmpty()) {
            errors.add(ValidationError.Locality)
        }
        if(event.phone.isEmpty()) {
            errors.add(ValidationError.PhoneNumber)
        }
        if(event.zip.isEmpty()) {
            errors.add(ValidationError.Zip)
        }
        if(event.houseNumber.isEmpty()) {
            errors.add(ValidationError.HouseNumber)
        }
        if(event.street.isEmpty()) {
            errors.add(ValidationError.Street)
        }
        return errors
    }

}