package com.example.app_picpayclone

import android.app.Application
import com.example.app_picpayclone.di.serviceModule
import com.example.app_picpayclone.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@AppApplication)
            modules(viewModelModule, serviceModule)
        }
    }


}