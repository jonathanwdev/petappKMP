package com.pet.app.presentation.commonComponents

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.pet.app.PlatformName
import com.pet.app.getPlatform
import com.pet.app.presentation.theme.gradient
import org.jetbrains.compose.resources.painterResource
import petapp.composeapp.generated.resources.Res
import petapp.composeapp.generated.resources.logo


@Composable
fun CommonLoader(modifier: Modifier = Modifier) {
    val isAndroid = getPlatform().name == PlatformName.ANDROID;

    val infiniteTransition = rememberInfiniteTransition(label = "animate_loader_event")
    val position by infiniteTransition.animateFloat(
        label = "animate_loader",
        initialValue = 0.2f,
        targetValue = 0.8f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 780, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .background(brush = gradient)
            .fillMaxSize()

    ) {
        Image(
            painterResource(
                Res.drawable.logo
            ),
            modifier = Modifier
                .width(120.dp)
                .offset(x = (position * if(isAndroid) 300 else 1000).dp),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
    }
}
