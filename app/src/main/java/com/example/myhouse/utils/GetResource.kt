package com.example.myhouse.utils

import com.example.myhouse.utils.Constants.CONNECTION_ERROR
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.internal.NopCollector.emit

abstract class GetResource {
    protected suspend fun <T> getResult(result: suspend () -> T) = flow {
        emit(Resource.Loading())
        try {
            val data = result()
            emit(Resource.Success(data))
        } catch (exception: Exception) {
            emit(Resource.Error(message = exception.message ?: CONNECTION_ERROR))
        }
    }.flowOn(Dispatchers.IO)

}