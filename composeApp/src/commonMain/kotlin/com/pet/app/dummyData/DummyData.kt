package com.pet.app.dummyData

import com.pet.app.domain.models.Pet
import com.pet.app.domain.models.PetBreeds
import com.pet.app.domain.models.PetContacts
import com.pet.app.domain.models.PetPhoto

val mockPet = Pet(
    id = 12345,
    type = "Dog",
    species = "Dog",
    breeds = PetBreeds(
        primary = "Pit Bull Terrier",
        mixed = false,
        unknown = false
    ),
    coat = "Short",
    age = "Baby",
    size = "Medium",
    status = "adoptable",
    color = "Black",
    contact = PetContacts(
        city = "Disney Land",
        state = "Disney Land",
        country = "Disney Land",
        postcode = "123",
        phone = "55 99 999999",
        email = "goodpet@mailcom"
    ),
    description = "A good pet to your home",
    organizationId = "1234",
    publishedAt = "123456",
    gender = "Male",
    tags = listOf(""),
    name = "Finn",
    photos = listOf(
        PetPhoto(
            full = "",
            small = "",
            large = "",
            medium = ""
        )
    )
)
