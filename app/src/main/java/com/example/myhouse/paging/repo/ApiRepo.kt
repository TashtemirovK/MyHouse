package com.example.myhouse.paging.repo

import com.example.myhouse.paging.api.ApiServices
import javax.inject.Inject

class ApiRepository @Inject constructor(
    private val apiServices: ApiServices
) {
    suspend fun getPopularAnimalList(page: Int) = apiServices.getPopularAnimalList(page)
    suspend fun getAnimalDetails(id: Int) = apiServices.getAnimalDetails(id)
}
