package com.pet.app.data.remote.models

import kotlinx.serialization.Serializable

@Serializable
data class PhotoResponse(
    val full: String,
    val large: String?,
    val medium: String,
    val small: String
)