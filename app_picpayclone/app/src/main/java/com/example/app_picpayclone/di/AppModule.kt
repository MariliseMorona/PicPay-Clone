package com.example.app_picpayclone.di

import com.example.app_picpayclone.ComponentesViewModel
import com.example.app_picpayclone.service.ApiService
import com.example.app_picpayclone.service.RetrofitService
import com.example.app_picpayclone.ui.ajuste.AjusteViewModel
import com.example.app_picpayclone.ui.home.HomeViewModel
import com.example.app_picpayclone.ui.login.LoginViewModel
import com.example.app_picpayclone.ui.pagar.PagarViewModel
import com.example.app_picpayclone.ui.transacao.TransacaoViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { ComponentesViewModel() }
    viewModel { HomeViewModel(get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { PagarViewModel(get()) }
    viewModel { AjusteViewModel() }
    viewModel { TransacaoViewModel(get()) }
}

val serviceModule = module {
    single { RetrofitService.create<ApiService>() }
}
//se for uma classe poderia usar o single ou o factory