package com.pet.app.presentation.screens.home


import android.util.Log
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pet.app.R
import com.pet.app.data.remote.InitializeKtor
import com.pet.app.domain.repository.Repository
import com.pet.app.presentation.commonComponents.CommonButton
import com.pet.app.presentation.commonComponents.CommonButtonColors
import com.pet.app.presentation.commonComponents.CommonOutlineButton
import com.pet.app.presentation.commonComponents.CommonOutlineButtonColors
import com.pet.app.presentation.components.TopAppBar
import com.pet.app.presentation.theme.PetAppTheme
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import petapp.composeapp.generated.resources.Res
import petapp.composeapp.generated.resources.home_heading
import petapp.composeapp.generated.resources.home_message
import petapp.composeapp.generated.resources.home_text
import petapp.composeapp.generated.resources.ic_play_circle

@Composable
fun HomeScreen(
    onNavigateToPetList: () -> Unit,
) {
    val repo = koinInject<Repository>()
    LaunchedEffect(Unit) {
        repo.getPets()
    }
    HomeView(
        onNavigateToPetList = onNavigateToPetList
    )
}

@Composable
private fun HomeView(
    onNavigateToPetList: () -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = androidx.compose.ui.graphics.Color.Transparent,
    ) { paddingValues ->
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopStart
        ) {
            Image(
                painter = painterResource(R.drawable.banner_large),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp)
                    .fillMaxSize()
            ) {
                Spacer(modifier = Modifier.height(5.dp))
                TopAppBar()
                Spacer(modifier = Modifier.height(40.dp))
                Text(
                    text = stringResource(Res.string.home_heading),
                    fontWeight = FontWeight.Bold,
                    fontSize = 43.sp,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = stringResource(Res.string.home_message),
                    fontWeight = FontWeight.Bold,
                    fontSize = 28.sp,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.height(18.dp))
                Text(
                    text = stringResource(Res.string.home_text),
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.secondary
                )
                Spacer(modifier = Modifier.height(32.dp))
                Row {
                    CommonOutlineButton(
                        color = CommonOutlineButtonColors.PRIMARY,
                        label = "View Intro",
                        onClick = {
                            onNavigateToPetList()
                        },
                        trailingIcon = Res.drawable.ic_play_circle,
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    CommonButton(
                        color = CommonButtonColors.PRIMARY,
                        label = "Explore Now",
                        onClick = {
                            onNavigateToPetList()
                        }
                    )
                }


            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun HomeViewPreview() {
    PetAppTheme {
        HomeView(
            onNavigateToPetList = {}
        )
    }
}