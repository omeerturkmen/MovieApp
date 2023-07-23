package com.example.myapplication2.data.di

import android.app.Application
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.myapplication2.BuildConfig
import com.example.myapplication2.data.remote.MovieService
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    private const val TIMEOUT = 60L

    @Provides
    @Singleton
    fun provideChuckInterceptor(application: Application) =
        ChuckerInterceptor.Builder(application).build()

    @Provides
    @Singleton
    fun provideOkHttpClient(chuckInterceptor: ChuckerInterceptor): OkHttpClient {
        return OkHttpClient.Builder().apply {
            if (BuildConfig.DEBUG) {
                addInterceptor(chuckInterceptor)
            }
            addInterceptor { chain ->
                val request =
                    chain.request().newBuilder().build()
                chain.proceed(request)
            }
            readTimeout(TIMEOUT, TimeUnit.SECONDS)
            connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            writeTimeout(TIMEOUT, TimeUnit.SECONDS)
        }.build()
    }

    @Provides
    @Singleton
    fun provideMovieService(okHttpClient: OkHttpClient): MovieService = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .build()
        .create(MovieService::class.java)
}