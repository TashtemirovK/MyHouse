package com.example.myhouse.domain.usecases.cameras

import com.example.myhouse.domain.CameraModel
import com.example.myhouse.domain.repositories.CamerasRepository
import javax.inject.Inject

class UpdateCameraUseCase @Inject constructor(
    private val cameraRepository: CamerasRepository
) {
    fun updateCamera(cameraModel: CameraModel) = cameraRepository.updateCamera(cameraModel)
}