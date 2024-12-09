package com.pet.app.presentation.navigation


import kotlinx.serialization.Serializable

interface PetNavRouts {
    @Serializable
    object Splash

    @Serializable
    object Home

    @Serializable
    object PetList

}