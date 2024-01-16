package com.example.myhouse.domain.usecases.cameras

import com.example.myhouse.domain.CameraModel
import com.example.myhouse.domain.repositories.CamerasRepository
import javax.inject.Inject

class DeleteCameraUseCase @Inject constructor(
    private val cameraRepository: CamerasRepository
) {
    fun deleteCamera(cameraModel: CameraModel) = cameraRepository.deleteCamera(cameraModel)
}