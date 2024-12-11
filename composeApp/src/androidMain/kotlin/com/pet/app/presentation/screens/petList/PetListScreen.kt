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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.pet.app.domain.models.Pet
import com.pet.app.dummyData.mockPet
import com.pet.app.presentation.commonComponents.CommonLoader
import com.pet.app.presentation.commonComponents.CommonRefreshButton
import com.pet.app.presentation.components.PetCard
import com.pet.app.presentation.components.PetCardData
import com.pet.app.presentation.theme.PetAppTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import petapp.composeapp.generated.resources.Res
import petapp.composeapp.generated.resources.bg_dogs
import petapp.composeapp.generated.resources.error_loading_pets
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Composable
fun PetListScreen(
    onNavigateToPetDetail: (id: Int) -> Unit,
    onNavigateBack: () -> Unit,
    viewModel: PetListViewModel = koinInject()
) {
    val lazyPagingItems = viewModel.pagingFlow.collectAsLazyPagingItems()

    PetListView(
        onNavigateToPetDetail = onNavigateToPetDetail,
        onNavigateBack = onNavigateBack,
        lazyPagingItems = lazyPagingItems
    )
}

@OptIn(ExperimentalUuidApi::class)
@Composable
private fun PetListView(
    onNavigateToPetDetail: (id: Int) -> Unit,
    onNavigateBack: () -> Unit,
    lazyPagingItems: LazyPagingItems<Pet>
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
                modifier = Modifier
                    .padding(start = 10.dp, top = 35.dp)
                    .clickable {
                        onNavigateBack()
                    },
                tint = MaterialTheme.colorScheme.onSurface,
            )
        }

        Spacer(Modifier.height(20.dp))
        LazyVerticalGrid(
            columns = GridCells.FixedSize(185.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.padding(horizontal = 5.dp),
        ) {

            if (lazyPagingItems.itemCount > 0) {
                items(
                    key = { Uuid.random().toHexString() },
                    count = lazyPagingItems.itemCount
                ) { item ->
                    val pet = lazyPagingItems[item]
                    pet?.let {
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
            }
            item(
                span = {
                    GridItemSpan(2)
                }
            ) {
                lazyPagingItems.apply {
                    when {
                        loadState.refresh is LoadState.Loading -> {
                            Spacer(Modifier.height(5.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                CircularProgressIndicator(
                                    modifier = Modifier.padding(10.dp)
                                )
                            }
                        }

                        loadState.append is LoadState.Loading -> {
                            Spacer(Modifier.height(5.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                CircularProgressIndicator(
                                    modifier = Modifier.padding(10.dp)
                                )
                            }
                        }

                        loadState.append is LoadState.Error -> {
                            Spacer(Modifier.height(25.dp))
                            CommonRefreshButton(
                                modifier = Modifier.fillMaxWidth(),
                                onClick = {
                                    lazyPagingItems.retry()
                                }
                            )
                            Spacer(Modifier.height(25.dp))
                        }
                    }
                }
            }
        }
    }

}
