package com.example.myhouse.domain.usecases.doors

class DeleteDoorUseCase @Inject constructor(
    private val doorRepository: DoorRepository
) {
    fun deleteDoor(doorModel: DoorModel) = doorRepository.deleteDoor(doorModel)
}