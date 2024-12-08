package com.pet.app.data.remote.dataSource

import com.pet.app.data.remote.InitializeKtor
import com.pet.app.data.remote.models.FetchAllPetsResponse
import com.pet.app.data.remote.models.FetchPetByIdResponse
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.url


class RemoteDataSourceImpl(
    private val client: InitializeKtor
): RemoteDataSource {

    override suspend fun fetchAllPets(page: Int): FetchAllPetsResponse {
        return client.client.get {
                url("animals")
                parameter("page", page)
            }
            .body<FetchAllPetsResponse>()
    }

    override suspend fun fetchPetById(id: Int): FetchPetByIdResponse {
        return client.client.get("animals/${id}").body<FetchPetByIdResponse>()
    }


}
