package com.pet.app.presentation.screens.petDetail

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import com.pet.app.dummyData.mockPet
import com.pet.app.presentation.commonComponents.CommonButton
import com.pet.app.presentation.commonComponents.CommonButtonColors
import com.pet.app.presentation.commonComponents.CommonLoader
import com.pet.app.presentation.commonComponents.CommonModal
import com.pet.app.presentation.commonComponents.CommonOutlineButton
import com.pet.app.presentation.commonComponents.CommonOutlineButtonColors
import com.pet.app.presentation.commonComponents.CommonPetDetailBox
import com.pet.app.presentation.commonComponents.CommonTableRow
import com.pet.app.presentation.components.TopBar
import com.pet.app.presentation.theme.PetAppTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import petapp.composeapp.generated.resources.Res
import petapp.composeapp.generated.resources.additi_info
import petapp.composeapp.generated.resources.adopted
import petapp.composeapp.generated.resources.age
import petapp.composeapp.generated.resources.chat_with_monito
import petapp.composeapp.generated.resources.color
import petapp.composeapp.generated.resources.contact_us
import petapp.composeapp.generated.resources.error_occurred
import petapp.composeapp.generated.resources.ic_chat_dots
import petapp.composeapp.generated.resources.leave
import petapp.composeapp.generated.resources.location
import petapp.composeapp.generated.resources.microchip
import petapp.composeapp.generated.resources.no
import petapp.composeapp.generated.resources.ops
import petapp.composeapp.generated.resources.place_holder_image
import petapp.composeapp.generated.resources.published
import petapp.composeapp.generated.resources.retryAsk
import petapp.composeapp.generated.resources.size
import petapp.composeapp.generated.resources.sku
import petapp.composeapp.generated.resources.unknown
import petapp.composeapp.generated.resources.vaccinated
import petapp.composeapp.generated.resources.yes

@Composable
fun PetDetailScreen(
    petId: Int,
    navHostController: NavHostController,
    viewModel: PetDetailViewModel = koinInject()
) {

    LaunchedEffect(Unit) {
        viewModel.getPetById(petId)
    }

    if (viewModel.uiState.isLoading) {
        return CommonLoader()
    }

    if (viewModel.uiState.hasError) {
        return CommonModal(
            onConfirmButtonLabel = stringResource(Res.string.retryAsk),
            onDismissButtonLabel = stringResource(Res.string.ops),
            title = stringResource(Res.string.leave),
            text = stringResource(Res.string.error_occurred),
            onDismiss = {
                viewModel.onDismissModal()
            },
            onConfirm = {
                viewModel.onRetry(petId)
            },
        )
    }

    Scaffold(
        topBar = {
            TopBar(
                modifier = Modifier.padding(horizontal = 30.dp),
                navHostController = navHostController
            )
        }
    ) { paddingValues ->
        PetDetailView(
            modifier = Modifier.padding(paddingValues),
            uiState = viewModel.uiState,
            onSelectPicture = viewModel::onSelectPicture
        )
    }
}

@Composable
private fun PetDetailView(
    modifier: Modifier = Modifier,
    uiState: PetDetailScreenState,
    onSelectPicture: (String) -> Unit
) {
    Spacer(modifier = Modifier.height(10.dp))
    Row(
        modifier = modifier
            .padding(horizontal = 30.dp, vertical = 10.dp)
            .clip(RoundedCornerShape(4.dp))
            .border(width = 1.dp, color = MaterialTheme.colorScheme.surface)
            .fillMaxSize()
    ) {
        uiState.pet?.let { pet ->
            Column(
                modifier = Modifier.weight(0.5f).padding(10.dp)
            ) {
                AsyncImage(
                    model = uiState.selectedPicture,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(476.dp)
                        .clip(RoundedCornerShape(10.dp)),
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(Res.drawable.place_holder_image),
                    error = painterResource(Res.drawable.place_holder_image),
                )
                Spacer(modifier = Modifier.height(10.dp))
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(pet.photos.size) { item ->
                        val photo = uiState.pet.photos[item].medium
                        val isSelected = photo == uiState.selectedPicture

                        AsyncImage(
                            model = photo,
                            contentDescription = null,
                            modifier = Modifier
                                .width(90.dp)
                                .height(90.dp)
                                .clip(RoundedCornerShape(6.dp))
                                .border(
                                    shape = RoundedCornerShape(6.dp),
                                    width = if (isSelected) 4.dp else 0.dp,
                                    color = MaterialTheme.colorScheme.background
                                )
                                .clickable {
                                    onSelectPicture(photo)
                                },
                            contentScale = ContentScale.Crop,
                            placeholder = painterResource(Res.drawable.place_holder_image),
                            error = painterResource(Res.drawable.place_holder_image),
                        )
                    }
                }
            }
            LazyColumn(
                modifier = Modifier.weight(0.5f).padding(10.dp)
            ) {
                item {
                    CommonPetDetailBox(
                        species = uiState.pet.species,
                        breed = uiState.pet.breeds.primary,
                        age = uiState.pet.age,
                        spaceBetween = true
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = uiState.pet.name,
                        fontSize = 19.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                }
                item {
                    Text(
                        text = uiState.pet.contact.email,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.secondary
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    Row {
                        CommonButton(
                            onClick = {},
                            label = stringResource(Res.string.contact_us),
                            color = CommonButtonColors.PRIMARY
                        )
                        Spacer(modifier = Modifier.width(11.dp))
                        CommonOutlineButton(
                            onClick = {},
                            leadingIcon = Res.drawable.ic_chat_dots,
                            modifier = Modifier.weight(1f),
                            label = stringResource(Res.string.chat_with_monito),
                            color = CommonOutlineButtonColors.PRIMARY
                        )
                    }
                }
                item {
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        CommonTableRow(
                            title = stringResource(Res.string.sku),
                            value = uiState.pet.id.toString()
                        )
                        CommonTableRow(
                            title = stringResource(Res.string.age),
                            value = uiState.pet.age
                        )
                        CommonTableRow(
                            title = stringResource(Res.string.size),
                            value = uiState.pet.size
                        )
                        CommonTableRow(
                            title = stringResource(Res.string.color),
                            value = uiState.pet.color ?: stringResource(Res.string.unknown)
                        )
                        CommonTableRow(
                            title = stringResource(Res.string.vaccinated),
                            value = stringResource(Res.string.yes)
                        )
                        CommonTableRow(
                            title = stringResource(Res.string.adopted),
                            value = if (uiState.pet.status == "adoptable") stringResource(Res.string.yes) else stringResource(
                                Res.string.no
                            )
                        )
                        CommonTableRow(
                            title = stringResource(Res.string.microchip),
                            value = stringResource(Res.string.yes)
                        )
                        CommonTableRow(
                            title = stringResource(Res.string.microchip),
                            value = stringResource(Res.string.yes)
                        )
                        CommonTableRow(
                            title = stringResource(Res.string.location),
                            value = uiState.pet.contact.country
                        )
                        CommonTableRow(
                            title = stringResource(Res.string.published),
                            value = uiState.pet.publishedAt
                        )
                        CommonTableRow(
                            title = stringResource(Res.string.additi_info),
                            value = uiState.pet.description
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun PetDetailViewPreview() {
    PetAppTheme {
        PetDetailView(
            uiState = PetDetailScreenState(
                pet = mockPet
            ),
            onSelectPicture = {}
        )
    }
}