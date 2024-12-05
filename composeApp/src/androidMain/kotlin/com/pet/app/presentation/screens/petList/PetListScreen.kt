package com.pet.app.presentation.screens.petList

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pet.app.presentation.components.PetCard
import com.pet.app.presentation.components.PetCardData
import com.pet.app.presentation.theme.PetAppTheme
import org.jetbrains.compose.resources.painterResource
import petapp.composeapp.generated.resources.Res
import petapp.composeapp.generated.resources.bg_dogs

@Composable
fun PetListScreen(
    onNavigateToPetDetail: () -> Unit
) {
    PetListView(
        onNavigateToPetDetail = onNavigateToPetDetail
    )
}

@Composable
private fun PetListView(
    onNavigateToPetDetail: () -> Unit
) {

    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surface)
            .fillMaxSize(),
    ) {
        Image(
            contentDescription = null,
            modifier = Modifier.fillMaxWidth(),
            painter = painterResource(Res.drawable.bg_dogs),
            contentScale = ContentScale.Crop
        )
        Spacer(Modifier.height(20.dp))
        LazyVerticalGrid(
            columns = GridCells.FixedSize(185.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.padding(horizontal = 5.dp),
        ) {
            items(20) {
                PetCard(
                    PetCardData(
                        id = "ID007",
                        name = "Dudu",
                        age = "1 year",
                        genre = "Male",
                        imageUrl = "https://i.natgeofe.com/n/4f5aaece-3300-41a4-b2a8-ed2708a0a27c/domestic-dog_thumb_square.jpg",
                        price = "1200",
                        breed = "Husky"
                    ),
                    onPetClick = {
                        onNavigateToPetDetail()
                    }
                )
            }
            item {
                Spacer(Modifier.height(10.dp))
            }
        }

    }
}

@Preview(showSystemUi = true)
@Composable
private fun PetListViewPreview() {
    PetAppTheme {
        PetListView(
            onNavigateToPetDetail = {}
        )
    }
}