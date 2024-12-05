package com.pet.app.presentation.screens.petDetail

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.pet.app.R
import com.pet.app.presentation.commonComponents.CommonButton
import com.pet.app.presentation.commonComponents.CommonButtonColors
import com.pet.app.presentation.commonComponents.CommonOutlineButton
import com.pet.app.presentation.commonComponents.CommonOutlineButtonColors
import com.pet.app.presentation.components.TableRow
import com.pet.app.presentation.theme.PetAppTheme
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.vectorResource
import petapp.composeapp.generated.resources.Res
import petapp.composeapp.generated.resources.ic_chat_dots
import petapp.composeapp.generated.resources.place_holder_image

@Composable
fun PetDetailScreen() {
    PetDetailView(
        onGoBack = {}
    )
}

@Composable
private fun PetDetailView(
    onGoBack: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(430.dp)
        ) {
            AsyncImage(
                model = "https://i.natgeofe.com/n/4f5aaece-3300-41a4-b2a8-ed2708a0a27c/domestic-dog_thumb_square.jpg",
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(Res.drawable.place_holder_image),
                error = painterResource(Res.drawable.place_holder_image),
            )
            Row(
                modifier = Modifier.paddingFromBaseline(top = 40.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowLeft,
                    contentDescription = null,
                    modifier = Modifier
                        .size(30.dp)
                        .clickable {
                            onGoBack()
                        },
                    )
            }

        }
        Spacer(modifier = Modifier.height(25.dp))
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
                            .padding(vertical = 8.dp)
                    ) {
                        Text("DOG")
                        Icon(
                            imageVector = Icons.Filled.KeyboardArrowRight,
                            contentDescription = null,
                            modifier = Modifier.size(30.dp)
                        )
                        Text("Young")
                        Icon(
                            imageVector = Icons.Filled.KeyboardArrowRight,
                            contentDescription = null,
                            modifier = Modifier.size(30.dp)
                        )
                        Text("American Bully")
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
                        text = "Albertus Caius",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "21999999999",
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

                    TableRow(title = "SKU", value = "#1000078")
                    TableRow(title = "Age", value = "2 months")
                    TableRow(title = "Size", value = "Small")
                    TableRow(title = "Color", value = "Appricot & Tan")
                    TableRow(title = "Vaccinated", value = "Yes")
                    TableRow(title = "Dewormed", value = "Yes")
                    TableRow(title = "Cert", value = "Yes")
                    TableRow(title = "Microchip", value = "Yes")
                    TableRow(title = "Location", value = "Brazil")
                    TableRow(title = "Published Date", value = "12 oct 2022")
                    TableRow(
                        title = "Additional Information",
                        value = "Pure breed Shih Tzu. Good body structure. With MKA cert and microchip. Father from champion lineage."
                    )
                }
            }
        }


    }

}

@Preview(showSystemUi = true)
@Composable
private fun PetDetailViewPreview() {
    PetAppTheme {
        PetDetailView(
            onGoBack = {}
        )
    }
}