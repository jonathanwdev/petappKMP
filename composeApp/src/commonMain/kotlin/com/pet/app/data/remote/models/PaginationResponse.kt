package com.pet.app.data.remote.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PaginationResponse(
    @SerialName("count_per_page")
    val countPerPage: Int,
    @SerialName("total_count")
    val totalCount: Int,
    @SerialName("current_page")
    val currentPage: Int,
    @SerialName("total_pages")
    val totalPages: Int
)
