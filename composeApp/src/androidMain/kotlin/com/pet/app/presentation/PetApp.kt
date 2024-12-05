package com.pet.app.presentation

import androidx.compose.runtime.Composable
import com.pet.app.presentation.navigation.PetNavHost
import org.koin.compose.KoinContext

@Composable
fun PetApp() {
    KoinContext {
        PetNavHost()
    }
}