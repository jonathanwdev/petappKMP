package com.pet.app.presentation.screens.petList

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pet.app.domain.repository.Repository
import kotlinx.coroutines.launch

class PetListViewModel(
    private val repository: Repository
) : ViewModel() {
    var uiState by mutableStateOf(PetListScreenState())
        private set

    fun fetchPetList() {
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true)
            repository.getPets().fold(
                onSuccess = {
                    uiState = uiState.copy(
                        pets = it.pets,
                        isLoading = false,
                    )
                },
                onFailure = {
                    uiState = uiState.copy(
                        isLoading = false,
                        hasError = true
                    )
                }
            )
        }
    }


    fun onPetSelected(petId: Int) {

    }
}