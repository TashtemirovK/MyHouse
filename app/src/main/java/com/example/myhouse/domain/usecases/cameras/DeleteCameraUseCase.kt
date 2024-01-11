package com.example.myhouse.domain.usecases.cameras

import com.example.myhouse.domain.CameraModel
import com.example.myhouse.domain.repositories.CameraRepository
import javax.inject.Inject

class DeleteCameraUseCase @Inject constructor(
    private val cameraRepository: CameraRepository
) {
    fun deleteCamera(cameraModel: CameraModel) = cameraRepository.deleteCamera(cameraModel)
}