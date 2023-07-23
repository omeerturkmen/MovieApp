package com.example.myapplication2.ui.search

import com.example.myapplication2.data.model.MovieBriefInfoModel

data class SearchState(
    val isLoading: Boolean,
    val trendingList: List<MovieBriefInfoModel>? = arrayListOf(),
    val searchList: List<MovieBriefInfoModel>? = arrayListOf(),
)
