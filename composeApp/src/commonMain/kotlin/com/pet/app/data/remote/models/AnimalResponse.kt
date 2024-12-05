package com.pet.app.data.remote.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnimalResponse(
    val age: String,
    val breeds: BreedsResponse,
    val coat: String?,
    val colors: ColorsResponse,
    val contact: ContactResponse,
    val description: String?,
    val gender: String,
    val id: Int,
    val name: String,
    @SerialName("organization_id")
    val organizationId: String,
    val photos: List<PhotoResponse>,
    @SerialName("published_at")
    val publishedAt: String,
    val size: String,
    val species: String,
    val status: String,
    val tags: List<String>,
    val type: String,
)