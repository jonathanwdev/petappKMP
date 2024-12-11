package com.pet.app.presentation.screens.petDetail


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pet.app.domain.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PetDetailViewModel(
    private val repository: Repository
): ViewModel() {
    var uiState by mutableStateOf(PetDetailScreenState())
        private set

    fun getPetById(petId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            uiState = uiState.copy(isLoading = true)
            repository.getPetById(petId).fold(
                onSuccess = {
                    uiState = uiState.copy(pet = it, isLoading = false, selectedPicture = it.photos.firstOrNull()?.medium)
                },
                onFailure = {
                    uiState = uiState.copy(isLoading = false, hasError = true)
                }
            )
        }
    }

    fun onDismissModal() {
        uiState = uiState.copy(isLoading = false, hasError = false)
    }

    fun onRetry(petId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            uiState = uiState.copy(isLoading = true)
            repository.getPetById(petId).fold(
                onSuccess = {
                    uiState = uiState.copy(pet = it, isLoading = false, selectedPicture = it.photos.firstOrNull()?.medium)
                },
                onFailure = {
                    uiState = uiState.copy(isLoading = false, hasError = true)
                }
            )
        }
    }

    fun onSelectPicture(picture: String) {
        uiState = uiState.copy(selectedPicture = picture)
    }
}