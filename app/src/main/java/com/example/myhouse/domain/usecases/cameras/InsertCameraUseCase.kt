package com.example.myhouse.domain.usecases.cameras

import com.example.myhouse.domain.CameraModel
import com.example.myhouse.domain.repositories.CameraRepository
import javax.inject.Inject

class InsertCameraUseCase @Inject constructor(
    private val cameraRepository: CameraRepository
) {
    fun insertCamera(cameraModel: CameraModel) = cameraRepository.insertCamera(cameraModel)
}