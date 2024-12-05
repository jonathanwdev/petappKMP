package com.pet.app.data.remote.models

import kotlinx.serialization.Serializable


@Serializable
data class AddressResponse(
    val city: String,
    val country: String,
    val postcode: String,
    val state: String
)