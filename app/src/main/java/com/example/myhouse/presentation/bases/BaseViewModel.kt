package com.example.myhouse.presentation.bases

import android.util.Log
import com.example.myhouse.utils.Resource
import com.google.android.gms.tflite.support.Empty
import kotlinx.coroutines.Dispatchers
import perfetto.protos.UiState
import java.util.concurrent.Flow

protected suspend fun <T> collectData(useCase: suspend () -> Flow<Resource<T>>): UiState<T> {
    var value: UiState<T> = UiState.Loading()
    useCase().flowOn(Dispatchers.IO).collect { resource ->
        Log.d("BaseViewModel", "Collecting data: $resource")

        value = when (resource) {
            is Resource.Loading -> UiState.Loading()
            is Resource.Success -> {
                val data = resource.data
                if (data != null) {
                    UiState.Success(data = data)
                } else {
                    UiState.Empty()
                }
            }
        }
        return value
    }
}


