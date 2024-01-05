package com.example.myhouse.data.api

import com.example.myhouse.data.local.models.Contact
import retrofit2.http.GET
import retrofit2.http.Path

interface ContactApi {
    @GET("contacts/{id}")
    suspend fun getContactById(@Path("id")id: Int): Contact
}