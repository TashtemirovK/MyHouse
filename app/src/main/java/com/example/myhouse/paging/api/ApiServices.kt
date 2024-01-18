package com.example.myhouse.paging.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServices {

    @GET("animal/popular")
    suspend fun getPopularAnimalsList(@Query("page") page: Int): Response<AnimalsListResponse>

    @GET("animal/{animal_id}")
    suspend fun getAnimalDetails(@Path("animal_id") id: Int): Response<AnimalDetailsResponse>

}
