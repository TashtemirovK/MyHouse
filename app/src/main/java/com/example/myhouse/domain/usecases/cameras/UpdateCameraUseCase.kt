package com.example.myhouse.domain.usecases.cameras

import com.example.myhouse.domain.CameraModel
import com.example.myhouse.domain.repositories.CameraRepository
import javax.inject.Inject

class UpdateCameraUseCase @Inject constructor(
    private val cameraRepository: CameraRepository
) {
    fun updateCamera(cameraModel: CameraModel) = cameraRepository.updateCamera(cameraModel)
}