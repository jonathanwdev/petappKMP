package com.pet.app.presentation.screens.petList

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.pet.app.presentation.commonComponents.CommonRefreshButton
import com.pet.app.presentation.components.PetCard
import com.pet.app.presentation.components.PetCardItem
import com.pet.app.presentation.components.TopBar
import com.pet.app.presentation.navigation.PetNavRouts
import com.pet.app.presentation.theme.PetAppTheme
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject
import petapp.composeapp.generated.resources.Res
import petapp.composeapp.generated.resources.bg_dogs

@Composable
fun PetListScreen(
    navController: NavHostController,
    viewModel: PetListViewModel = koinInject()
) {
    val lazyGridState = rememberLazyGridState()
    val uiState = viewModel.uiState

    LaunchedEffect(lazyGridState, uiState.isLoading) {
        snapshotFlow { lazyGridState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
            .collect { lastVisibleItemIndex ->
                if (lastVisibleItemIndex == uiState.pets.size - 1 && !uiState.isLoading) {
                    viewModel.getPets()
                }
            }
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.surface,
        topBar = {
            TopBar(
                modifier = Modifier.padding(horizontal = 30.dp),
                navHostController = navController
            )
        },
    ) { paddingValues ->
        PetListView(
            modifier = Modifier.padding(paddingValues),
            uiState = uiState,
            lazyGridState = lazyGridState,
            onPetItemClick = { petId ->
                navController.navigate(PetNavRouts.PetDetail(petId))
            },
            onRefresh = viewModel::onRefresh
        )
    }
}

@Composable
private fun PetListView(
    modifier: Modifier = Modifier,
    uiState: PetListScreenState,
    lazyGridState: LazyGridState,
    onPetItemClick: (petId: Int) -> Unit,
    onRefresh: () -> Unit
) {
    val scrollState = rememberScrollState(
        initial = 0
    )
    Box(
        modifier = modifier.padding(horizontal = 30.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            Image(
                painter = painterResource(Res.drawable.bg_dogs),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth().height(260.dp)
            )
            Spacer(modifier = Modifier.height(15.dp))
            LazyVerticalGrid(
                columns = GridCells.FixedSize(250.dp),
                modifier = Modifier.height(700.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalArrangement = Arrangement.spacedBy(10.dp),
                state = lazyGridState
            ) {
                items(uiState.pets.size) { item ->
                    val pet = uiState.pets[item]
                    PetCard(
                        onPetClick = {
                            onPetItemClick(pet.id)
                        },
                        petItem = PetCardItem(
                            photo = pet.photos.firstOrNull()?.medium,
                            age = pet.age,
                            genre = pet.gender,
                            breed = pet.breeds.primary,
                            adoptable = pet.status == "adoptable"
                        )
                    )
                }
                if (uiState.isLoading) {
                    item(
                        span = {
                            GridItemSpan(4)
                        }
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }
                if (uiState.hasError) {
                    item(
                        span = {
                            GridItemSpan(4)
                        }
                    ) {
                        CommonRefreshButton(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = {
                                onRefresh()
                            }
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun PetListViewPreview() {
    PetAppTheme {
        PetListView(
            uiState = PetListScreenState(),
            lazyGridState = LazyGridState(),
            onPetItemClick = {},
            onRefresh = {}
        )
    }
}