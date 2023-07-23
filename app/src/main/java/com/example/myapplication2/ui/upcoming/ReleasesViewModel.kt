package com.example.myapplication2.ui.upcoming

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
class ReleasesViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {
    private val _state: MutableStateFlow<ReleasesState> =
        MutableStateFlow(ReleasesState(isLoading = false))
    val state: StateFlow<ReleasesState> get() = _state

    init {
        getUpcoming()
        getNowPlaying()
    }

    private fun getUpcoming() = viewModelScope.launch {
        movieRepository.getUpcoming().collect {
            when (it) {
                is Resource.Success -> _state.value = state.value.copy(
                    upcomingList = it.data?.results,
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

    private fun getNowPlaying() = viewModelScope.launch {
        movieRepository.getNowPlaying().collect {
            when (it) {
                is Resource.Success -> _state.value = state.value.copy(
                    nowPlayingList = it.data?.results,
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