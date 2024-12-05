package com.pet.app.data.remote.dataSource

import com.pet.app.data.remote.models.FetchAllPetsResponse

interface RemoteDataSource {
    suspend fun fetchAllPets(): FetchAllPetsResponse
}