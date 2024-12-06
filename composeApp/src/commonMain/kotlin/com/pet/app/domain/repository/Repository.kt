package com.pet.app.domain.repository

import com.pet.app.domain.models.Pet
import com.pet.app.domain.models.PetList

interface Repository {
    suspend fun getPets(): Result<PetList>
    suspend fun getPetById(id: Int): Result<Pet>
}