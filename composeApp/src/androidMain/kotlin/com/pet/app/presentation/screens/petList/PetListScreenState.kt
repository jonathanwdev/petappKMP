package com.pet.app.presentation.screens.petList

import com.pet.app.domain.models.Pet

data class PetListScreenState(
    val pets: List<Pet> = emptyList(),
    val isLoading: Boolean = false,
    val hasError: Boolean = false,
)
