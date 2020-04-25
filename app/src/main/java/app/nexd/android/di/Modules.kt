package app.nexd.android.di

import app.nexd.android.Api
import app.nexd.android.Preferences
import app.nexd.android.ui.MainViewModel
import app.nexd.android.ui.auth.login.LoginViewModel
import app.nexd.android.ui.auth.register.RegisterDetailedViewModel
import app.nexd.android.ui.auth.register.RegisterViewModel
import app.nexd.android.ui.helper.checkout.CheckoutViewModel
import app.nexd.android.ui.helper.delivery.DeliveryViewModel
import app.nexd.android.ui.helper.detail.HelperDetailViewModel
import app.nexd.android.ui.helper.requestOverview.HelperOverviewViewModel
import app.nexd.android.ui.helper.shoppingList.ShoppingListViewModel
import app.nexd.android.ui.helper.transcript.TranscriptViewModel
import app.nexd.android.ui.role.RoleViewModel
import app.nexd.android.ui.seeker.call.PhoneCallViewModel
import app.nexd.android.ui.seeker.create.SeekerCreateRequestViewModel
import app.nexd.android.ui.seeker.detail.SeekerDetailViewModel
import app.nexd.android.ui.seeker.overview.SeekerOverviewViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single { Preferences(get()) }

    single { Api(preferences = get()) }

    viewModel { MainViewModel(get()) }

    viewModel { RoleViewModel(get(), get()) }

    viewModel { RegisterViewModel(get(), get()) }

    viewModel { RegisterDetailedViewModel(get()) }

    viewModel { LoginViewModel(get(), get()) }

    viewModel { HelperOverviewViewModel(get(), get()) }

    viewModel { TranscriptViewModel(get()) }

    viewModel { CheckoutViewModel(get()) }

    viewModel { HelperDetailViewModel(get()) }

    viewModel { ShoppingListViewModel(get()) }

    viewModel { DeliveryViewModel(get()) }

    viewModel { SeekerDetailViewModel(get()) }

    viewModel { PhoneCallViewModel(get()) }

    viewModel { SeekerOverviewViewModel(get()) }

    viewModel { SeekerCreateRequestViewModel(get()) }
}