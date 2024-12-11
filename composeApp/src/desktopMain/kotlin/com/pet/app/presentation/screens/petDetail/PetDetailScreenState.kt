package com.pet.app.presentation.screens.petDetail

import com.pet.app.domain.models.Pet

data class PetDetailScreenState(
    val pet: Pet? = null,
    val isLoading: Boolean = false,
    val hasError: Boolean = false,
    val selectedPicture: String? = null,
)