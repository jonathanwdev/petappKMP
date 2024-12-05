package com.pet.app

import PetAppDesktop
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.pet.app.di.startKoinDesktop



fun main() = application {
    startKoinDesktop()

    Window(
        onCloseRequest = ::exitApplication,
        title = "PetApp",
    ) {
       PetAppDesktop()
    }
}



