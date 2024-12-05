package com.pet.app.domain.models


data class PageData(
    val countPerPage: Int,
    val totalCount: Int,
    val currentPage: Int,
    val totalPages: Int
)
