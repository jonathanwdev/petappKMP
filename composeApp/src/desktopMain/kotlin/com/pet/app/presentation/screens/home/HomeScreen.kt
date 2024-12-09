package com.pet.app.presentation.screens.home

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.pet.app.presentation.commonComponents.CommonButton
import com.pet.app.presentation.commonComponents.CommonButtonColors
import com.pet.app.presentation.commonComponents.CommonOutlineButton
import com.pet.app.presentation.commonComponents.CommonOutlineButtonColors
import com.pet.app.presentation.components.TopBar
import com.pet.app.presentation.theme.PetAppTheme
import com.pet.app.utils.LoadImage
import org.jetbrains.compose.resources.stringResource
import petapp.composeapp.generated.resources.Res
import petapp.composeapp.generated.resources.home_heading
import petapp.composeapp.generated.resources.home_message
import petapp.composeapp.generated.resources.home_text
import petapp.composeapp.generated.resources.ic_play_circle

@Composable
fun HomeScreen(
    onNavigateToPetList: () -> Unit,
) {
    HomeView(
        onNavigateToPetList = onNavigateToPetList,
    )
}

@Composable
private fun HomeView(
    onNavigateToPetList: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopBar(
                modifier = Modifier.padding(horizontal = 30.dp)
            )
        }
    ) {
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

                Spacer(Modifier.height(80.dp))
                Text(
                    text = stringResource(Res.string.home_heading),
                    fontSize = 50.sp,
                    fontWeight = FontWeight.Black,
                    color = MaterialTheme.colorScheme.secondary
                )
                Text(
                    text = stringResource(Res.string.home_message),
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.secondary
                )
                Spacer(Modifier.height(20.dp))
                Text(
                    text = stringResource(Res.string.home_text),
                    modifier = Modifier.width(410.dp),
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
                Spacer(modifier = Modifier.height(30.dp))
                Row {
                    CommonOutlineButton(
                        color = CommonOutlineButtonColors.PRIMARY,
                        label = "View Intro",
                        onClick = onNavigateToPetList,
                        trailingIcon = Res.drawable.ic_play_circle,
                    )
                    Spacer(Modifier.width(20.dp))
                    CommonButton(
                        color = CommonButtonColors.PRIMARY,
                        label = "Explore Now",
                        onClick = onNavigateToPetList
                    )
                }
            }
        }
    }


}

@Preview
@Composable
private fun HomePreview() {
    PetAppTheme {
        HomeView(
            onNavigateToPetList = {},
        )
    }
}

