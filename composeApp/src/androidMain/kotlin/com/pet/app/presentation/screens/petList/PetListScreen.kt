package com.pet.app.presentation.screens.petList

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pet.app.presentation.components.PetCard
import com.pet.app.presentation.components.PetCardData
import com.pet.app.presentation.theme.PetAppTheme
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject
import petapp.composeapp.generated.resources.Res
import petapp.composeapp.generated.resources.bg_dogs

@Composable
fun PetListScreen(
    onNavigateToPetDetail: (id: Int) -> Unit,
    onNavigateBack: () -> Unit,
    viewModel: PetListViewModel = koinInject()
) {
    LaunchedEffect(Unit) {
        viewModel.fetchPetList()
    }
    PetListView(
        onNavigateToPetDetail = onNavigateToPetDetail,
        onNavigateBack = onNavigateBack,
        uiState = viewModel.uiState
    )
}

@Composable
private fun PetListView(
    onNavigateToPetDetail: (id: Int) -> Unit,
    onNavigateBack: () -> Unit,
    uiState: PetListScreenState
) {

    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surface)
            .fillMaxSize(),
    ) {
        Box {
            Image(
                contentDescription = null,
                modifier = Modifier.fillMaxWidth(),
                painter = painterResource(Res.drawable.bg_dogs),
                contentScale = ContentScale.Crop
            )
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                modifier = Modifier.padding(start = 10.dp, top = 35.dp).clickable {
                    onNavigateBack()
                },
                tint = MaterialTheme.colorScheme.onSurface,
            )
        }


        Spacer(Modifier.height(20.dp))
        if(uiState.isLoading) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.padding(10.dp)
                )
            }

        }
        LazyVerticalGrid(
            columns = GridCells.FixedSize(185.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.padding(horizontal = 5.dp),
        ) {
            if(uiState.pets.isNotEmpty()) {
                items(
                    key = { idx -> uiState.pets[idx].id },
                    count = uiState.pets.size,
                ) { item ->
                    val pet = uiState.pets[item]
                    PetCard(
                        PetCardData(
                            name = pet.name,
                            age = pet.age,
                            genre = pet.gender,
                            imageUrl = pet.photos.firstOrNull()?.medium,
                            status = pet.status,
                            breed = pet.breeds.primary
                        ),
                        onPetClick = {
                            onNavigateToPetDetail(pet.id)
                        }
                    )
                }
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
            onNavigateToPetDetail = {},
            onNavigateBack = {},
            uiState = PetListScreenState()
        )
    }
}