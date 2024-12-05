package com.pet.app.data.remote.dataSource

import com.pet.app.data.remote.InitializeKtor
import com.pet.app.data.remote.models.FetchAllPetsResponse
import io.ktor.client.call.body
import io.ktor.client.request.get


class RemoteDataSourceImpl(
    private val client: InitializeKtor
): RemoteDataSource {

    override suspend fun fetchAllPets(): FetchAllPetsResponse {
        return client.client.get("animals").body<FetchAllPetsResponse>()
    }
}
