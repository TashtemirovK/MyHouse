package com.example.myhouse.viewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.myhouse.paging.repo.ApiRepository
import com.example.myhouse.paging.response.AnimalDetailsResponse
import com.example.myhouse.viewModel.LoadingState.Success
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnimalViewModel @Inject constructor(private val repository: ApiRepository) : ViewModel() {

    private val _loading = MutableLiveData<LoadingState>()
    val loading: LiveData<LoadingState> get() = _loading

    val animalsList = Pager(PagingConfig(1)) {
        AnimalPagingSource(repository)
    }.flow.cachedIn(viewModelScope)

    private val _animalDetails = MutableLiveData<AnimalDetailsResponse>()
    val animalDetails: LiveData<AnimalDetailsResponse> get() = _animalDetails

    fun loadAnimalDetails(id: Int) = viewModelScope.launch {
        _loading.value = LoadingState.Loading
        val response = repository.getAnimalDetails(id)
        if (response.isSuccessful) {
            val details = response.body()
            if (details != null) {
                _animalDetails.value = details
                _loading.value = Success
            } else {
                _loading.value = LoadingState.Error("Empty response body")
            }
        } else {
            _loading.value = LoadingState.Error("Failed to load animal details")
        }
    }
}

sealed class LoadingState {
    object Loading : LoadingState()
    data class Success(val message: String? = null) : LoadingState()
    data class Error(val errorMessage: String) : LoadingState()
}
