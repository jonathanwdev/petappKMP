package com.pet.app.domain.mapper

import com.pet.app.data.remote.models.AnimalResponse
import com.pet.app.data.remote.models.BreedsResponse
import com.pet.app.data.remote.models.ContactResponse
import com.pet.app.data.remote.models.PhotoResponse
import com.pet.app.domain.models.Pet
import com.pet.app.domain.models.PetBreeds
import com.pet.app.domain.models.PetContacts
import com.pet.app.domain.models.PetPhoto

class MapPetToDomain : Mapper<List<Pet>, List<AnimalResponse>> {
    companion object {
        private const val EMPTY_DATA = "unknown"
    }

    private fun formatData(data: String?): String {
        return data ?: EMPTY_DATA
    }

    override fun mapToDomain(entity: List<AnimalResponse>): List<Pet> {
        return entity.map { petItem ->
            Pet(
                color = petItem.colors.primary,
                id = petItem.id,
                name = petItem.name,
                gender = petItem.gender,
                photos = this.formatPhotos(petItem.photos),
                age = petItem.age,
                coat = petItem.coat,
                size = petItem.size,
                tags = petItem.tags,
                type = petItem.type,
                breeds = formatBreeds(petItem.breeds),
                organizationId = petItem.organizationId,
                status = petItem.status,
                contact = this.formatContact(petItem.contact),
                species = petItem.species,
                description = this.formatData(petItem?.description),
                publishedAt = petItem.publishedAt
            )
        }
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