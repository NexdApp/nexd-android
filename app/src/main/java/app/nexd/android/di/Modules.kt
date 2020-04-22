package app.nexd.android.di

import app.nexd.android.Api
import app.nexd.android.Preferences
import app.nexd.android.ui.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single { Preferences(get()) }

    single { Api() }
    
    viewModel { MainViewModel(get()) }

}