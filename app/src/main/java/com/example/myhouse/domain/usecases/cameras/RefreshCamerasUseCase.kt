package com.example.myhouse.domain.usecases.cameras

import com.example.myhouse.domain.CameraModel
import com.example.myhouse.domain.repositories.CamerasRepository
import java.util.concurrent.Flow
import javax.inject.Inject

class RefreshCamerasUseCase @Inject constructor(
    private val cameraRepository: CamerasRepository
) {
    suspend operator fun invoke(): Flow<Resource<List<CameraModel>>> {

        cameraRepository.getRemoteCameras().collect { resource ->
            if (resource is Resource.Success) {
                if (cameraRepository.getLocalCameras().isEmpty()) {
                    cameraRepository.insertLocalCameras(resource.data!!)
                } else {
                    cameraRepository.updateLocalCameras(resource.data!!)
                }
            }
        }
        return cameraRepository.getRemoteCameras()
    }

}