package com.example.myapplication2.ui.main

import com.example.myapplication2.data.model.MovieBriefInfoModel

data class MainState(
    val isLoading: Boolean,
    val topRatedList: List<MovieBriefInfoModel>? = arrayListOf(),
    val popularList: List<MovieBriefInfoModel>? = arrayListOf()
)
