package com.pet.app.presentation.screens.petDetail

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.pet.app.dummyData.mockPet
import com.pet.app.presentation.commonComponents.CommonButton
import com.pet.app.presentation.commonComponents.CommonButtonColors
import com.pet.app.presentation.commonComponents.CommonLoader
import com.pet.app.presentation.commonComponents.CommonOutlineButton
import com.pet.app.presentation.commonComponents.CommonOutlineButtonColors
import com.pet.app.presentation.commonComponents.CommonModal
import com.pet.app.presentation.commonComponents.CommonPetDetailBox
import com.pet.app.presentation.commonComponents.CommonTableRow
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
import petapp.composeapp.generated.resources.information
import petapp.composeapp.generated.resources.leave
import petapp.composeapp.generated.resources.location
import petapp.composeapp.generated.resources.microchip
import petapp.composeapp.generated.resources.no
import petapp.composeapp.generated.resources.ops
import petapp.composeapp.generated.resources.place_holder_image
import petapp.composeapp.generated.resources.published
import petapp.composeapp.generated.resources.retryAsk
import petapp.composeapp.generated.resources.share
import petapp.composeapp.generated.resources.size
import petapp.composeapp.generated.resources.sku
import petapp.composeapp.generated.resources.unknown
import petapp.composeapp.generated.resources.vaccinated
import petapp.composeapp.generated.resources.yes

@Composable
fun PetDetailScreen(
    petId: Int,
    onNavigateBack: () -> Unit,
    viewModel: PetDetailViewModel = koinInject()
) {

    LaunchedEffect(true) {
        viewModel.getPetById(petId)
    }

    PetDetailView(
        onNavigateBack = onNavigateBack,
        onDismissModal = {
            viewModel.onDismissModal()
            onNavigateBack()
        },
        onRetry = {
            viewModel.onRetry(petId)
        },
        onSelectPicture = viewModel::onSelectPicture,
        uiState = viewModel.uiState
    )
}

@Composable
private fun PetDetailView(
    onNavigateBack: () -> Unit,
    onDismissModal: () -> Unit,
    onRetry: () -> Unit,
    onSelectPicture: (String) -> Unit,
    uiState: PetDetailScreenState
) {
    if (uiState.isLoading) {
        return CommonLoader()
    }

    if (uiState.hasError) {
        CommonModal(
            onConfirmButtonLabel = stringResource(Res.string.retryAsk),
            onDismissButtonLabel = stringResource(Res.string.ops),
            title = stringResource(Res.string.leave),
            text = stringResource(Res.string.error_occurred),
            onDismiss = {
                onDismissModal()
            },
            onConfirm = {
                onRetry()
            },
        )
    }
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(430.dp)
        ) {
            AsyncImage(
                model = uiState.selectedPicture,
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(Res.drawable.place_holder_image),
                error = painterResource(Res.drawable.place_holder_image),
            )
            uiState.pet?.let { pet ->
                LazyRow(
                    modifier = Modifier.fillMaxWidth().align(Alignment.BottomCenter),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(pet.photos.size) { item ->
                        val photo = pet.photos[item].medium
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
                                    width = if(isSelected) 4.dp else 0.dp,
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

            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .paddingFromBaseline(top = 40.dp)
                    .padding(horizontal = 15.dp)
            ) {
                IconButton(
                    onClick = onNavigateBack,
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(35.dp)
                        .background(MaterialTheme.colorScheme.secondary)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.surface,
                        modifier = Modifier
                            .size(30.dp)

                    )
                }
            }

        }
        Spacer(modifier = Modifier.height(25.dp))
        if (uiState.pet != null) {
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 10.dp)
            ) {

                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth(),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 2.dp
                        ),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.primaryContainer,
                        ),
                        shape = RoundedCornerShape(
                            20.dp
                        )
                    ) {
                        CommonPetDetailBox(
                            species = uiState.pet.species,
                            breed = uiState.pet.breeds.primary,
                            age = uiState.pet.age
                        )
                    }
                }
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = uiState.pet.name,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = uiState.pet.contact.email,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.secondary
                        )
                        Spacer(modifier = Modifier.height(16.dp))
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
                }
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = stringResource(Res.string.information),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.secondary
                        )
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Share,
                                contentDescription = null,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.height(5.dp))
                            Text(
                                text = stringResource(Res.string.share),
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.secondary
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(12.dp))
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

@Preview(showSystemUi = true)
@Composable
private fun PetDetailViewPreviewNoData() {
    PetAppTheme {
        PetDetailView(
            onNavigateBack = {},
            uiState = PetDetailScreenState(isLoading = false),
            onRetry = {},
            onSelectPicture = {},
            onDismissModal = {}
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun PetDetailViewPreviewLoading() {
    PetAppTheme {
        PetDetailView(
            onNavigateBack = {},
            uiState = PetDetailScreenState(isLoading = true),
            onRetry = {},
            onSelectPicture = {},
            onDismissModal = {}
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun PetDetailViewPreviewWithData() {
    PetAppTheme {
        PetDetailView(
            onNavigateBack = {},
            uiState = PetDetailScreenState(pet = mockPet),
            onRetry = {},
            onSelectPicture = {},
            onDismissModal = {}
        )
    }
}