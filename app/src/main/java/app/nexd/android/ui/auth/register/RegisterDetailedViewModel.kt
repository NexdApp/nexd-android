package app.nexd.android.ui.auth.register

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import app.nexd.android.Preferences
import app.nexd.android.api
import app.nexd.android.api.model.RegisterDto
import app.nexd.android.api.model.UpdateUserDto
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy

class RegisterDetailedViewModel(application: Application) : AndroidViewModel(application) {

    fun register(
        firstName: String,
        lastName: String,
        email: String,
        password: String,
        phoneNumber: String,
        street: String,
        houseNumber: String,
        zipCode: String,
        city: String
    ): LiveData<Boolean> {
        val response = MutableLiveData<Boolean>()
        with(api) {
            authControllerRegister(
                RegisterDto()
                    .firstName(firstName)
                    .lastName(lastName)
                    .email(email)
                    .password(password)
            )
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap { responseTokenDto ->
                    api.setBearerToken(responseTokenDto.accessToken)
                    with(getApplication<Application>().applicationContext) {
                        Preferences.setToken(this, responseTokenDto.accessToken)
                    }

                    api.userControllerUpdateMyself(
                        UpdateUserDto()
                            .telephone(phoneNumber)
                            .street(street)
                            .number(houseNumber)
                            .zipCode(zipCode)
                            .city(city)
                    )
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    response.value = true
                }
        }
        return response
    }

}