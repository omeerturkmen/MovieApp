package com.example.myapplication2.data.model

data class MovieDetailedInformationModel(
    val adult: Boolean?,
    val backdrop_path: String?,
    val belongs_to_collection: CollectionModel?,
    val budget: Int?,
    val genres: List<GenresModel>?,
    val homepage: String?,
    val id: Long?,
    val imdb_id: String?,
    val original_language: String?,
    val original_title: String?,
    val overview: String?,
    val popularity: Double?,
    val poster_path: String?,
    val productionCompanies: List<ProductionCompanyModel>?,
    val productionCountries: List<ProductionCountryModel>?,
    val release_date: String?,
    val revenue: Long?,
    val runtime: Int?,
    val spokenLanguages: List<SpokenLanguageModel>?,
    val status: String?,
    val tagline: String?,
    val title: String?,
    val video: Boolean?,
    val vote_average: Double?,
    val vote_count: Int?
)
