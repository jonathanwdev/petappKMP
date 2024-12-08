package com.pet.app

import PetAppDesktop
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import com.pet.app.di.startKoinDesktop
import com.pet.app.presentation.theme.PetAppTheme


fun main() = application {
    startKoinDesktop()

    Window(
        onCloseRequest = ::exitApplication,
        title = "PetApp",
        resizable = false,
        state = WindowState(
            width = 1200.dp,
            height = 800.dp
        )
    ) {
        PetAppTheme {
            PetAppDesktop()
        }
    }
}



