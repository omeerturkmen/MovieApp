package com.example.myapplication2.data.di

import com.example.myapplication2.data.remote.MovieService
import com.example.myapplication2.data.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideMovieRepository(movieService: MovieService): MovieRepository =
        MovieRepository(movieService)
}