package com.example.myhouse.paging.pagingAdapter

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.myhouse.paging.repo.ApiRepository

class AnimalsPagingSource(
    private val repository: ApiRepository,
) : PagingSource<Int, AnimalsListResponse.Result>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AnimalsListResponse.Result> {
        return try {
            val currentPage = params.key ?: 1
            val response = repository.getPopularAnimalsList(currentPage)

            if (response.isSuccessful) {
                val data = response.body()?.results ?: emptyList()

                LoadResult.Page(
                    data = data,
                    prevKey = if (currentPage == 1) null else currentPage - 1,
                    nextKey = if (data.isEmpty()) null else currentPage + 1
                )
            } else {
                LoadResult.Error(Exception(""))
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, AnimalsListResponse.Result>): Int? {
        return null
    }
}
