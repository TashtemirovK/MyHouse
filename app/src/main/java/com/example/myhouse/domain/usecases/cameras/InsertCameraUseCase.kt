package com.example.myhouse.domain.usecases.cameras

import com.example.myhouse.domain.CameraModel
import com.example.myhouse.domain.repositories.CamerasRepository
import javax.inject.Inject

class InsertCameraUseCase @Inject constructor(
    private val cameraRepository: CamerasRepository
) {
    fun insertCamera(cameraModel: CameraModel) = cameraRepository.insertCamera(cameraModel)
}