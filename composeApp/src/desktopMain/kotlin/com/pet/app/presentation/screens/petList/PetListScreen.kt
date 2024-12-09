package com.pet.app.presentation.screens.petList

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import com.pet.app.domain.models.Pet
import com.pet.app.presentation.components.PetCard
import com.pet.app.presentation.components.TopBar
import com.pet.app.presentation.theme.PetAppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.swing.Swing
import kotlinx.coroutines.withContext
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject
import petapp.composeapp.generated.resources.Res
import petapp.composeapp.generated.resources.bg_dogs

@Composable
fun PetListScreen(
    viewModel: PetListViewModel = koinInject()
) {
    LaunchedEffect(Unit) {
        viewModel.getPets()
    }
    PetListView(
    )
}

@Composable
private fun PetListView(
) {
    val scrollState = rememberScrollState(
        initial = 0
    )

    Scaffold(
        topBar = {
            TopBar(
                modifier = Modifier.padding(horizontal = 30.dp)
            )
        },
        containerColor = MaterialTheme.colorScheme.surface,
        modifier = Modifier
    ) { paddingValues ->
        Box(
            modifier = Modifier.padding(horizontal = 30.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(paddingValues)
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
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
//                    items(pagingItems.itemCount) {
////                        PetCard()
//                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun PetListViewPreview() {
    PetAppTheme {
        PetListView()
    }
}