package com.example.myhouse.domain.repositories

import com.example.myhouse.domain.CameraModel
import com.example.myhouse.utils.Resource
import java.util.concurrent.Flow

interface CamerasRepository {

    suspend fun getRemoteCameras(): Flow<Resource<List<CameraModel>>>

    fun getLocalCameras(): List<CameraModel>

    fun insertCamera(cameraModel: CameraModel)

    fun insertLocalCameras(cameraModels: List<CameraModel>)

    fun updateCamera(cameraModel: CameraModel)

    fun updateLocalCameras(cameraModels: List<CameraModel>)

    fun deleteCamera(cameraModel: CameraModel)
}