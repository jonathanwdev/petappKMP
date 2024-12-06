package com.pet.app.domain.mapper

import com.pet.app.data.remote.models.AnimalResponse
import com.pet.app.data.remote.models.BreedsResponse
import com.pet.app.data.remote.models.ContactResponse
import com.pet.app.data.remote.models.PhotoResponse
import com.pet.app.domain.models.Pet
import com.pet.app.domain.models.PetBreeds
import com.pet.app.domain.models.PetContacts
import com.pet.app.domain.models.PetPhoto

class MapSinglePetToDomain: Mapper<Pet, AnimalResponse> {
    companion object {
        private const val EMPTY_DATA = "unknown"
    }

    private fun formatData(data: String?): String {
        return data ?: EMPTY_DATA
    }

    override fun mapToDomain(entity: AnimalResponse): Pet {
        return Pet(
                color = entity.colors.primary,
                id = entity.id,
                name = entity.name,
                gender = entity.gender,
                photos = this.formatPhotos(entity.photos),
                age = entity.age,
                coat = entity.coat,
                size = entity.size,
                tags = entity.tags,
                type = entity.type,
                breeds = formatBreeds(entity.breeds),
                organizationId = entity.organizationId,
                status = entity.status,
                contact = this.formatContact(entity.contact),
                species = entity.species,
                description = this.formatData(entity?.description),
                publishedAt = entity.publishedAt
            )
    }

    private fun formatContact(contact: ContactResponse?): PetContacts {
        return PetContacts(
            email = formatData(contact?.email),
            phone = formatData(contact?.phone),
            city = formatData(contact?.address?.city),
            state = formatData(contact?.address?.state),
            postcode = formatData(contact?.address?.postcode),
            country = formatData(contact?.address?.country)
        )
    }

    private fun formatBreeds(breeds: BreedsResponse): PetBreeds {
        return PetBreeds(
            primary = formatData(breeds.primary),
            mixed = breeds.mixed,
            unknown = false
        )
    }

    private fun formatPhotos(photos: List<PhotoResponse>): List<PetPhoto> {
        return photos.map {
            PetPhoto(
                full = it.full,
                large = it.large,
                medium = it.medium,
                small = it.small
            )
        }
    }
}