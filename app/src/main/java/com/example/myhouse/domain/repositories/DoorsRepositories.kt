package com.example.myhouse.domain.repositories

import com.example.myhouse.data.dtos.DoorsDto

interface DoorsRepositories {
    suspend fun getAllHouses(): List<DoorsDto>

}