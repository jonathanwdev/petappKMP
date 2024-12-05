package com.pet.app.data.remote.models

import kotlinx.serialization.Serializable

@Serializable
data class ColorsResponse(
    val primary: String?,
)