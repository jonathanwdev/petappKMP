package com.pet.app.presentation.screens.petList

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pet.app.domain.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch



class PetListViewModel(
    private val repository: Repository
) : ViewModel() {
    var uiState by mutableStateOf(PetListScreenState())
        private set

    init {
        getPets()
    }

    fun getPets() {
        uiState = uiState.copy(isLoading = true, hasError = false)
        viewModelScope.launch(Dispatchers.IO) {
            repository.getPets(uiState.pageData.currentPage + 1).fold(
                onSuccess = { response ->
                    uiState = uiState.copy(
                        pets = uiState.pets + response.pets,
                        pageData = response.pageData,
                        isLoading = false
                    )
                },
                onFailure = {
                    uiState = uiState.copy(hasError = true, isLoading = false)
                }
            )
        }
    }

    fun onRefresh() {
        uiState = uiState.copy(isLoading = true, hasError = false)
        viewModelScope.launch(Dispatchers.IO) {
            val retryPage = if(uiState.pageData.currentPage > 0) uiState.pageData.currentPage else 1
            repository.getPets(retryPage).fold(
                onSuccess = { response ->
                    uiState = uiState.copy(
                        pets = uiState.pets + response.pets,
                        pageData = response.pageData,
                        isLoading = false
                    )
                },
                onFailure = {
                    uiState = uiState.copy(hasError = true, isLoading = false)
                }
            )
        }
    }


}