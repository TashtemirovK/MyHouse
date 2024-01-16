package com.example.myhouse.doors

import androidx.lifecycle.viewModelScope
import com.example.my.camera_monitoring_7month.domain.usecases.doors.GetAllDoorsUseCase
import com.example.my.camera_monitoring_7month.domain.usecases.doors.InsertDoorUseCase
import com.example.my.camera_monitoring_7month.domain.usecases.doors.RefreshDoorsUseCase
import com.example.my.camera_monitoring_7month.domain.usecases.doors.UpdateDoorUseCase
import com.example.my.camera_monitoring_7month.domain.utils.Resource
import com.example.my.camera_monitoring_7month.presentation.base.BaseViewModel
import com.example.myhouse.domain.DoorModel
import com.example.myhouse.domain.usecases.doors.DeleteDoorUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import perfetto.protos.UiState
import javax.inject.Inject

@HiltViewModel
class DoorsViewModel @Inject constructor(
    private val getAllDoorsUseCase: GetAllDoorsUseCase,
    private val refreshDoorsUseCase: RefreshDoorsUseCase,
    private val insertDoorUseCase: InsertDoorUseCase,
    private val updateDoorUseCase: UpdateDoorUseCase,
    private val deleteDoorUseCase: DeleteDoorUseCase
) : BaseViewModel() {

    private val _doorsList = MutableStateFlow<UiState<List<DoorModel>>>(UiState.Loading())
    val doorsList: StateFlow<UiState<List<DoorModel>>> = _doorsList

    fun getAllDoors() = doRequest {
        getAllDoorsUseCase()
    }

    fun refreshDoors() = doRequest {
        refreshDoorsUseCase()
    }

    private fun doRequest(useCase: suspend () -> Flow<Resource<List<DoorModel>>>) {
        viewModelScope.launch(Dispatchers.IO) {
            _doorsList.value = collectData { useCase() }
        }
    }

}
