package com.example.myapplication2.data.model

data class UpcomingModel(
    val dates: UpcomingDatesModel?,
    val page: Int?,
    val results: List<MovieBriefInfoModel>?,
    val total_pages: Int?,
    val total_results: Int?
)
