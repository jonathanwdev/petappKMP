package com.pet.app

enum class PlatformName {
    ANDROID,
    DESKTOP
}

interface Platform {
    val name: PlatformName
}

expect fun getPlatform(): Platform