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
import kotlinx.coroutines.flow.Flow

class PetListViewModel(
    private val repository: Repository
) : ViewModel() {
    val pagingFlow: Flow<PagingData<Pet>> = Pager(PagingConfig(pageSize = 20)) {
        GetPetsPagingUseCase(repository)
    }.flow.cachedIn(viewModelScope)

}