package com.pet.app.presentation.screens.petDetail

import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
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
import androidx.compose.ui.text.style.TextOverflow
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
import com.pet.app.presentation.components.TableRow
import com.pet.app.presentation.theme.PetAppTheme
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.koinInject
import petapp.composeapp.generated.resources.Res
import petapp.composeapp.generated.resources.ic_chat_dots
import petapp.composeapp.generated.resources.place_holder_image

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
        uiState = viewModel.uiState
    )
}

@Composable
private fun PetDetailView(
    onNavigateBack: () -> Unit,
    onDismissModal: () -> Unit = {},
    onRetry: () -> Unit,
    uiState: PetDetailScreenState
) {
    if (uiState.isLoading) {
        return CommonLoader()
    }

    if(uiState.hasError) {
        CommonModal(
            onConfirmButtonLabel = "Try again ?",
            onDismissButtonLabel = "Leave",
            title = "Oops..",
            text = "An Error has occurred",
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
                model = uiState.pet?.photos?.firstOrNull()?.medium,
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(Res.drawable.place_holder_image),
                error = painterResource(Res.drawable.place_holder_image),
            )
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
                        Row(
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp, horizontal = 16.dp)
                        ) {
                            Text(uiState.pet.species)
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                                contentDescription = null,
                                modifier = Modifier.size(30.dp)
                            )
                            Text(uiState.pet.age)
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                                contentDescription = null,
                                modifier = Modifier.size(30.dp)
                            )
                            Text(
                                text = uiState.pet.breeds.primary,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,

                                )
                        }

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
                                label = "Contact us",
                                color = CommonButtonColors.PRIMARY
                            )
                            Spacer(modifier = Modifier.width(11.dp))
                            CommonOutlineButton(
                                onClick = {},
                                leadingIcon = Res.drawable.ic_chat_dots,
                                modifier = Modifier.weight(1f),
                                label = "Chat with monito",
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
                            text = "Information",
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
                                text = "Share",
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

                        TableRow(title = "SKU", value = uiState.pet.id.toString())
                        TableRow(title = "Age", value = uiState.pet.age)
                        TableRow(title = "Size", value = uiState.pet.size)
                        TableRow(title = "Color", value = uiState.pet.color ?: "unknown")
                        TableRow(title = "Vaccinated", value = "Yes")
                        TableRow(
                            title = "Adopted",
                            value = if (uiState.pet.status == "adoptable") "Yes" else "No"
                        )
                        TableRow(title = "Cert", value = "Yes")
                        TableRow(title = "Microchip", value = "Yes")
                        TableRow(title = "Location", value = uiState.pet.contact.country)
                        TableRow(title = "Published Date", value = uiState.pet.publishedAt)
                        TableRow(
                            title = "Additional Information",
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
            onRetry = {}
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
            onRetry = {}
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
            onRetry = {}
        )
    }
}