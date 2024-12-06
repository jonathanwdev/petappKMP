package com.pet.app.domain.repository

import com.pet.app.data.remote.dataSource.RemoteDataSource
import com.pet.app.domain.mapper.MapPetListToDomain
import com.pet.app.domain.mapper.MapSinglePetToDomain
import com.pet.app.domain.models.PageData
import com.pet.app.domain.models.Pet
import com.pet.app.domain.models.PetList

class RepositoryImpl(
    private val dataSource: RemoteDataSource
): Repository {

    override suspend fun getPets(): Result<PetList> {
        return runCatching {
            val petList = dataSource.fetchAllPets();
            PetList(
                pets = MapPetListToDomain().mapToDomain(petList.animals),
                pageData = PageData(
                    countPerPage = petList.pagination.countPerPage,
                    totalCount = petList.pagination.totalCount,
                    currentPage = petList.pagination.currentPage,
                    totalPages = petList.pagination.totalPages
                )
            )

        }
    }

    override suspend fun getPetById(id: Int):  Result<Pet> {
        return runCatching {
            val pet = dataSource.fetchPetById(id)
            MapSinglePetToDomain().mapToDomain(pet.animal)

        }
    }
}