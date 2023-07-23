package com.example.myapplication2.ui.upcoming

import com.example.myapplication2.data.model.MovieBriefInfoModel

data class ReleasesState(
    val isLoading: Boolean,
    val upcomingList: List<MovieBriefInfoModel>? = arrayListOf(),
    val nowPlayingList: List<MovieBriefInfoModel>? = arrayListOf()
)
