package com.pet.app.presentation.theme


import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable


private val lightScheme = lightColorScheme(
    primary = primary,
    primaryContainer = primaryContainer,
    tertiary = tertiary,
    secondary = secondary,
    onPrimary = onPrimary,
    surface = surface,
    onPrimaryContainer = onPrimaryContainer,
    onSecondaryContainer = onSecondaryContainer,
    onTertiaryContainer = onTertiaryContainer,
    background = background
)


@Composable
fun PetAppTheme(
    darkTheme: Boolean = true,
    content: @Composable() () -> Unit
) {
    val colorScheme = when {
        darkTheme -> lightScheme
        else -> lightScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        content = content
    )
}

