package com.example.myapplication2.ui.search

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
class SearchViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {
    private val _state: MutableStateFlow<SearchState> =
        MutableStateFlow(SearchState(isLoading = false))
    val state: StateFlow<SearchState> get() = _state

    init {
        getTrending()
    }

    private fun getTrending() = viewModelScope.launch {
        movieRepository.getTrending().collect {
            when (it) {
                is Resource.Success -> _state.value = state.value.copy(
                    trendingList = it.data?.results,
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

    fun getSearch(query: String) = viewModelScope.launch {
        movieRepository.getSearch(query).collect {
            when (it) {
                is Resource.Success -> _state.value = state.value.copy(
                    searchList = it.data?.results,
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