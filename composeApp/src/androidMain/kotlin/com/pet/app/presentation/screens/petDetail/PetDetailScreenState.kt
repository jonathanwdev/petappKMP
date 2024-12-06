package com.pet.app.presentation.screens.petDetail

import com.pet.app.domain.models.Pet

data class PetDetailScreenState(
    val isLoading: Boolean = false,
    val pet: Pet? = null,
    val hasError: Boolean = false,
)
