package com.pet.app

import androidx.compose.runtime.Immutable

@Immutable
class JVMPlatform: Platform {
    override val name: PlatformName = PlatformName.DESKTOP
}

actual fun getPlatform(): Platform = JVMPlatform()