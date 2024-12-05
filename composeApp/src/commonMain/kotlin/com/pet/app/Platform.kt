package com.pet.app

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform