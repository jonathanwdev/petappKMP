package com.pet.app.domain.useCase

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.pet.app.domain.models.Pet
import com.pet.app.domain.repository.Repository

class GetPetsPagingUseCase(
    private val repository: Repository
) : PagingSource<Int, Pet>() {
    private var currentPage = 1

    override fun getRefreshKey(state: PagingState<Int, Pet>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pet> {
        val nextPageNumber = params.key ?: 1
        val response = repository.getPets(nextPageNumber)
        response.fold(
            onSuccess = { res ->
                currentPage = res.pageData.currentPage
                return LoadResult.Page(
                    data = res.pets,
                    prevKey = if (nextPageNumber == 1) null else nextPageNumber - 1,
                    nextKey = if (res.pets.isEmpty()) null else nextPageNumber + 1
                )
            },
            onFailure = {
                return LoadResult.Error(it)
            }

        )

    }


}