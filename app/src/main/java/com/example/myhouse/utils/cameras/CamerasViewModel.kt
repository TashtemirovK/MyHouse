package com.example.myhouse.utils.cameras

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.my.camera_monitoring_7month.presentation.base.BaseViewModel
import com.example.myhouse.domain.CameraModel
import com.example.myhouse.domain.usecases.cameras.DeleteCameraUseCase
import com.example.myhouse.domain.usecases.cameras.GetAllCamerasUseCase
import com.example.myhouse.domain.usecases.cameras.InsertCameraUseCase
import com.example.myhouse.domain.usecases.cameras.RefreshCamerasUseCase
import com.example.myhouse.domain.usecases.cameras.UpdateCameraUseCase
import com.example.myhouse.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import perfetto.protos.UiState
import javax.inject.Inject


@HiltViewModel
class CamerasViewModel @Inject constructor(
    private val getAllCamerasUseCase: GetAllCamerasUseCase,
    private val refreshCamerasUseCase: RefreshCamerasUseCase,
    private val insertCameraUseCase: InsertCameraUseCase,
    private val updateCameraUseCase: UpdateCameraUseCase,
    private val deleteCameraUseCase: DeleteCameraUseCase
) : BaseViewModel() {

    private val _camerasList = MutableLiveData<UiState<List<CameraModel>>>(UiState.Loading())
    val camerasList = _camerasList


    fun getAllCameras() = doRequest {
        getAllCamerasUseCase()
    }

    fun refreshCameras() = doRequest {
        refreshCamerasUseCase()
    }

    private fun doRequest(useCase: suspend () -> Flow<Resource<List<CameraModel>>>) {
        viewModelScope.launch(Dispatchers.IO) {
            _camerasList.value = collectData { useCase() }
        }
    }

}