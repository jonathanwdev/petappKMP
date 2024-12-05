package com.pet.app.data.remote.models

import kotlinx.serialization.Serializable

@Serializable
data class BreedsResponse(
    val mixed: Boolean,
    val primary: String,
    val unknown: Boolean
)