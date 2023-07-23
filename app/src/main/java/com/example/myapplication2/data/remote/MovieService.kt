package com.example.myapplication2.data.remote

import com.example.myapplication2.data.model.MovieDetailedInformationModel
import com.example.myapplication2.data.model.MovieModel
import com.example.myapplication2.data.model.UpcomingModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {
    @GET("movie/top_rated")
    suspend fun getTopRated(
        @Query("api_key")
        apiKey: String = "69e57e7ef62ea4756b388882eb0f3a13",
        @Query("language")
        language: String = "en",
        @Query("page")
        page: Int = 1
    ): MovieModel

    @GET("movie/popular")
    suspend fun getPopular(
        @Query("api_key")
        apiKey: String = "69e57e7ef62ea4756b388882eb0f3a13",
        @Query("language")
        language: String = "en",
        @Query("page")
        page: Int = 1
    ): MovieModel

    @GET("movie/upcoming")
    suspend fun getUpcoming(
        @Query("api_key")
        apiKey: String = "69e57e7ef62ea4756b388882eb0f3a13",
        @Query("language")
        language: String = "en",
        @Query("page")
        page: Int = 1
    ): UpcomingModel

    @GET("movie/now_playing")
    suspend fun getNowPlaying(
        @Query("api_key")
        apiKey: String = "69e57e7ef62ea4756b388882eb0f3a13",
        @Query("language")
        language: String = "en",
        @Query("page")
        page: Int = 1
    ): UpcomingModel

    @GET("trending/movie/day")
    suspend fun getTrending(
        @Query("api_key")
        apiKey: String = "69e57e7ef62ea4756b388882eb0f3a13",
        @Query("language")
        language: String = "en",
        @Query("page")
        page: Int = 1
    ): MovieModel

    @GET("search/movie")
    suspend fun getSearch(
        @Query("query")
        query: String,
        @Query("api_key")
        apiKey: String = "69e57e7ef62ea4756b388882eb0f3a13",
        @Query("language")
        language: String = "en",
        @Query("page")
        page: Int = 1
    ): MovieModel

    @GET("movie/{id}")
    suspend fun getDetails(
        @Path("id")
        movieId: String,
        @Query("api_key")
        apiKey: String = "69e57e7ef62ea4756b388882eb0f3a13",
        @Query("language")
        language: String = "en",
        @Query("page")
        page: Int = 1
    ): MovieDetailedInformationModel
}