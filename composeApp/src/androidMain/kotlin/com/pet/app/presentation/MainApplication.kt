package com.pet.app.presentation

import android.app.Application
import com.pet.app.di.startKoinAndroid


class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoinAndroid(this@MainApplication)
    }
}