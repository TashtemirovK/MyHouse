package com.example.myhouse.di

import com.example.myhouse.data.repositories.CameraRepositoriesImpl
import com.example.myhouse.domain.repositories.DoorsRepositories
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindCameraRepository(cameraRepositoryImpl: CameraRepositoriesImpl): CameraRepositoriesImpl

    @Binds
    fun bindDoorRepository(doorRepositoryImpl: DoorsRepositories): DoorsRepositories
}