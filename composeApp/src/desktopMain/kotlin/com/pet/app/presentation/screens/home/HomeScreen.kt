package com.pet.app.presentation.screens.home

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.pet.app.presentation.components.TopBar
import com.pet.app.presentation.theme.PetAppTheme
import com.pet.app.utils.LoadImage

@Composable
fun HomeScreen() {
    HomeView()
}

@Composable
private fun HomeView() {
    Box {
        Image(
            bitmap = LoadImage("home_bg_desktop.jpg"),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier.padding(horizontal = 30.dp)
        ) {
            TopBar(
                currRoute = "Home"
            )
        }
    }

}

@Preview
@Composable
private fun HomePreview() {
    PetAppTheme {
        HomeView()
    }
}

