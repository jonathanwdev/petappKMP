package com.pet.app.presentation.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun HomeScreen() {
    HomeView()
}

@Composable
private fun HomeView() {
    Column {
        Text("Home screen")
    }
}

