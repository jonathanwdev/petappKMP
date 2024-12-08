package com.pet.app.presentation.screens.petDetail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pet.app.domain.repository.Repository
import kotlinx.coroutines.launch

class PetDetailViewModel(
    private val repository: Repository,
): ViewModel() {


    var uiState by mutableStateOf(PetDetailScreenState())
        private set

    fun getPetById(id: Int) {
        uiState = uiState.copy(isLoading = true, hasError = false)
        viewModelScope.launch {
            repository.getPetById(id).fold(
                onSuccess = { pet ->
                    uiState = uiState.copy(pet = pet, isLoading = false)
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

    fun onRetry(id: Int) {
        uiState = uiState.copy(isLoading = true, hasError = false)
        viewModelScope.launch {
            repository.getPetById(id).fold(
                onSuccess = { pet ->
                    uiState = uiState.copy(pet = pet, isLoading = false)
                },
                onFailure = {
                    uiState = uiState.copy(isLoading = false, hasError = true)
                }
            )
        }
    }
}