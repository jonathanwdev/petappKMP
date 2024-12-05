package com.pet.app.di


import org.koin.core.context.startKoin

fun startKoinDesktop() {
    startKoin {
        modules(CommonModule)
    }

}