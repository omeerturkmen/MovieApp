package com.example.myapplication2.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication2.core.util.Resource
import com.example.myapplication2.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {
    private val _state: MutableStateFlow<DetailState> =
        MutableStateFlow(DetailState(isLoading = false))
    val state: StateFlow<DetailState> get() = _state

    fun getDetails(movieId: String) = viewModelScope.launch {
        movieRepository.getDetails(movieId).collect {
            when (it) {
                is Resource.Success -> _state.value = state.value.copy(
                    isLoading = false,
                    myData = it.data
                )
                is Resource.Loading -> _state.value = state.value.copy(
                    isLoading = true
                )
                is Resource.Error -> _state.value = state.value.copy(
                    isLoading = false
                )
            }
        }
    }
}