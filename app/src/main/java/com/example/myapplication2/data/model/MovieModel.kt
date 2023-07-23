package com.example.myapplication2.data.model

data class MovieModel(
    val page: Int?,
    val results: List<MovieBriefInfoModel>?,
    val total_pages: Int?,
    val total_results: Int?
)
