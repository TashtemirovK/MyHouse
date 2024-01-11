package com.example.myhouse.data.repositories

import com.example.myhouse.data.local.db.CameraDao
import javax.inject.Inject

class CameraRepositoriesImpl @Inject constructor(
    private val dao: CameraDao,
    private val houseApiService: HouseApiService,
) : CameraRepositoriesImpl, GetResource() {
    override suspend fun getRemoteCameras() = getResult {
        houseApiService.getCamera().body()!!.data.cameras.toDomainModel()
    }

    override fun getLocalCameras(): List<CameraModel> {
        return dao.getAllCameras().toDomainModel()
    }

    override fun insertCamera(cameraModel: CameraModel) {
        dao.insertCamera(cameraModel.toDataDto())
    }

    override fun insertLocalCameras(cameraModels: List<CameraModel>) {
        dao.insertAllCameras(cameraModels.toDataDto())
    }

    override fun updateCamera(cameraModel: CameraModel) {
        dao.updateCamera(cameraModel.toDataDto())
    }

    override fun updateLocalCameras(cameraModels: List<CameraModel>) {
        dao.updateAllCameras(cameraModels.toDataDto())
    }

    override fun deleteCamera(cameraModel: CameraModel) {
        dao.deleteCamera(cameraModel.toDataDto())
    }

}