package com.example.myhouse.domain.usecases.doors

class UpdateCameraUseCase @Inject constructor(
    private val cameraRepository: CameraRepository
) {
    fun updateCamera(cameraModel: CameraModel) = cameraRepository.updateCamera(cameraModel)
}