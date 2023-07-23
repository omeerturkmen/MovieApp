package com.example.myapplication2.ui.detail

import com.example.myapplication2.data.model.MovieDetailedInformationModel

data class DetailState(
    val isLoading: Boolean,
    val myData: MovieDetailedInformationModel? = null
)
