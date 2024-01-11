package com.example.myhouse.domain.usecases.doors

class InsertCameraUseCase @Inject constructor(
    private val cameraRepository: CameraRepository
) {
    fun insertCamera(cameraModel: CameraModel) = cameraRepository.insertCamera(cameraModel)
}