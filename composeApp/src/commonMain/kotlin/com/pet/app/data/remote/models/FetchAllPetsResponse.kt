package com.pet.app.data.remote.models

import kotlinx.serialization.Serializable

@Serializable
data class FetchAllPetsResponse(
    val animals: List<AnimalResponse>,
    val pagination: PaginationResponse
)
