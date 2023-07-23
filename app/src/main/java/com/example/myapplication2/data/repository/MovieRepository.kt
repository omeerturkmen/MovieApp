package com.example.myapplication2.data.repository

import com.example.myapplication2.core.util.Resource
import com.example.myapplication2.data.model.MovieDetailedInformationModel
import com.example.myapplication2.data.remote.MovieService
import com.example.myapplication2.data.model.MovieModel
import com.example.myapplication2.data.model.UpcomingModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class MovieRepository(private val movieService: MovieService) {

    fun getTopRated(): Flow<Resource<MovieModel>> = flow {
        try {
            emit(Resource.Loading())
            val response = movieService.getTopRated()
            emit(Resource.Success(response))
        } catch (e: HttpException) {
            emit(Resource.Error("Error"))
        }
    }

    fun getPopular(): Flow<Resource<MovieModel>> = flow {
        try {
            emit(Resource.Loading())
            val response = movieService.getPopular()
            emit(Resource.Success(response))
        } catch (e: HttpException) {
            emit(Resource.Error("Error"))
        }
    }

    fun getUpcoming(): Flow<Resource<UpcomingModel>> = flow {
        try {
            emit(Resource.Loading())
            val response = movieService.getUpcoming()
            emit(Resource.Success(response))
        } catch (e: HttpException) {
            emit(Resource.Error("Error"))
        }
    }

    fun getNowPlaying(): Flow<Resource<UpcomingModel>> = flow {
        try {
            emit(Resource.Loading())
            val response = movieService.getNowPlaying()
            emit(Resource.Success(response))
        } catch (e: HttpException) {
            emit(Resource.Error("Error"))
        }
    }

    fun getTrending(): Flow<Resource<MovieModel>> = flow {
        try {
            emit(Resource.Loading())
            val response = movieService.getTrending()
            emit(Resource.Success(response))
        } catch (e: HttpException) {
            emit(Resource.Error("Error"))
        }
    }

    fun getSearch(query: String): Flow<Resource<MovieModel>> = flow {
        try {
            emit(Resource.Loading())
            val response = movieService.getSearch(query)
            emit(Resource.Success(response))
        } catch (e: HttpException) {
            emit(Resource.Error("Error"))
        }
    }

    fun getDetails(movieId: String): Flow<Resource<MovieDetailedInformationModel>> = flow {
        try {
            emit(Resource.Loading())
            val response = movieService.getDetails(movieId)
            emit(Resource.Success(response))
        } catch (e: HttpException) {
            emit(Resource.Error("Error"))
        }
    }
}