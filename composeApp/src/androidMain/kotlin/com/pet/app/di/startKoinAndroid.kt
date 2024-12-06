package com.pet.app.di

import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

fun startKoinAndroid(androidContext: Context) {
    startKoin {
        androidContext(androidContext)
        modules(CommonModule, androidModules)
    }

}