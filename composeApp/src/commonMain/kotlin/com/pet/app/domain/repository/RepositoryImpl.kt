package com.pet.app.domain.repository

import com.pet.app.data.remote.dataSource.RemoteDataSource
import com.pet.app.domain.mapper.MapPetToDomain
import com.pet.app.domain.models.PageData
import com.pet.app.domain.models.PetList

class RepositoryImpl(
    private val dataSource: RemoteDataSource
): Repository {

    override suspend fun getPets(): Result<PetList> {
        return runCatching {
            val petList = dataSource.fetchAllPets();
            PetList(
                pets = MapPetToDomain().mapToDomain(petList.animals),
                pageData = PageData(
                    countPerPage = petList.pagination.countPerPage,
                    totalCount = petList.pagination.totalCount,
                    currentPage = petList.pagination.currentPage,
                    totalPages = petList.pagination.totalPages
                )
            )

        }
    }
}