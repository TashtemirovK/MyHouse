package com.example.myhouse.data.repositories

import com.example.myhouse.data.api.HouseApi
import com.example.myhouse.data.dtos.DoorsDto
import com.example.myhouse.data.dtos.toDataDto
import com.example.myhouse.data.dtos.toDomainModel
import com.example.myhouse.data.local.db.DoorDao
import com.example.myhouse.domain.DoorModel
import com.example.myhouse.domain.repositories.DoorsRepositories
import javax.inject.Inject

class DoorsRepositoriesImpl @Inject constructor(
    private val dao: DoorDao,
    private val houseApi: HouseApi,
) : DoorsRepositories, GetResource() {
    override suspend fun getRemoteDoors() = getResult {
        houseApi.getDoor().body()!!.data.toDomainModel()
    }

    override fun getLocalDoors(): List<DoorModel> {
        return dao.getAllDoors().toDomainModel()
    }

    override fun insertDoor(doorModel: DoorModel) {
        dao.insertDoor(doorModel.toDataDto())
    }

    override fun insertLocalDoors(doorModels: List<DoorModel>) {
        dao.insertAllDoors(doorModels.toDataDto())
    }

    override fun updateDoor(doorModel: DoorModel) {
        dao.updateDoor(doorModel.toDataDto())
    }

    override fun updateLocalDoors(doorModels: List<DoorModel>) {
        dao.updateAllDoors(doorModels.toDataDto())
    }

    override fun deleteDoor(doorModel: DoorModel) {
        dao.deleteDoor(doorModel.toDataDto())
    }

}