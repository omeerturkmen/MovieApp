package com.example.myapplication2.ui.main

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
class MainViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {
    private val _state: MutableStateFlow<MainState> =
        MutableStateFlow(MainState(isLoading = false))
    val state: StateFlow<MainState> get() = _state

    init {
        getTopRated()
        getPopular()
    }

    private fun getTopRated() = viewModelScope.launch {
        movieRepository.getTopRated().collect {
            when (it) {
                is Resource.Success -> _state.value = state.value.copy(
                    topRatedList = it.data?.results,
                    isLoading = false
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

    private fun getPopular() = viewModelScope.launch {
        movieRepository.getPopular().collect {
            when (it) {
                is Resource.Success -> _state.value = state.value.copy(
                    popularList = it.data?.results,
                    isLoading = false
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