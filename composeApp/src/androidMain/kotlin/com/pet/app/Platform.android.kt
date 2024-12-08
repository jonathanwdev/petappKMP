package com.pet.app

import android.os.Build
import androidx.compose.runtime.Immutable


@Immutable
class AndroidPlatform : Platform {
    override val name: PlatformName = PlatformName.ANDROID
}

actual fun getPlatform(): Platform = AndroidPlatform()