package com.neocafe.neocafewaiter.application

import android.app.Application
import com.neocafe.neocafewaiter.di.retrofitModule
import com.neocafe.neocafewaiter.di.viewModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(){
            modules(listOf(retrofitModule, viewModule))
            androidContext(this@MyApplication)
        }
    }
}