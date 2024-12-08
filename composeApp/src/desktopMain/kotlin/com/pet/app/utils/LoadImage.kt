package com.pet.app.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.loadImageBitmap
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.decodeToImageBitmap
import java.io.File
import java.io.FileInputStream
import java.io.InputStream
import kotlin.io.path.Path
import kotlin.io.path.absolute

@OptIn(ExperimentalResourceApi::class)
@Composable
fun LoadImage(path: String): ImageBitmap {
    val resourcePath = "/AndroidStudioProjects/PetApp/composeApp/src/desktopMain/desktopResources/drawable/${path}"
    val imageFile = File(System.getProperty("user.home"), resourcePath)
    val imageStream: InputStream = FileInputStream(imageFile)
    return imageStream.readAllBytes().decodeToImageBitmap()
}