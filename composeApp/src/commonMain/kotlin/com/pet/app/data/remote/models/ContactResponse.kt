package com.pet.app.data.remote.models

import kotlinx.serialization.Serializable

@Serializable
data class ContactResponse(
    val address: AddressResponse,
    val email: String?,
    val phone: String?
)