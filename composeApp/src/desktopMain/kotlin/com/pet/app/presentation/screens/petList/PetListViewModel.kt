package com.pet.app.presentation.screens.petList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.pet.app.domain.models.Pet
import com.pet.app.domain.repository.Repository
import com.pet.app.domain.useCase.GetPetsPagingUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import kotlinx.coroutines.swing.Swing
import kotlinx.coroutines.withContext

class PetListViewModel(
    private val repository: Repository
) : ViewModel() {

    suspend fun getPets() {
        repository.getPets(1)
    }


}