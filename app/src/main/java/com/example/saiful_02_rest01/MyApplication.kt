package com.example.saiful_02_rest01

import android.app.Application
import com.example.saiful_02_rest01.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // Koin Start
        startKoin {
            androidContext(this@MyApplication)
            modules(networkModule)
        }
    }
}