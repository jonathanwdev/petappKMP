package com.pet.app.domain.models

data class Pet(
    val age: String,
    val breeds: PetBreeds,
    val coat: String?,
    val color: String?,
    val contact: PetContacts,
    val description: String,
    val gender: String,
    val id: Int,
    val name: String,
    val organizationId: String,
    val photos: List<PetPhoto>,
    val publishedAt: String,
    val size: String,
    val species: String,
    val status: String,
    val tags: List<String>,
    val type: String,
)