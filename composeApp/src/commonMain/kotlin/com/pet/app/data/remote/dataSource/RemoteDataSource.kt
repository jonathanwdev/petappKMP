package com.pet.app.data.remote.dataSource

import com.pet.app.data.remote.models.FetchAllPetsResponse
import com.pet.app.data.remote.models.FetchPetByIdResponse

interface RemoteDataSource {
    suspend fun fetchAllPets(page: Int): FetchAllPetsResponse
    suspend fun fetchPetById(id: Int): FetchPetByIdResponse

}