package com.jean.cinemappcompose.core.util

import com.jean.cinemappcompose.core.data.network.MoviesApiService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun createMoviesApiServiceMock(baseUrl: String): MoviesApiService {

    val okHttpClient = OkHttpClient
        .Builder()
        .build()

    return Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(baseUrl)
        .client(okHttpClient)
        .build().create(MoviesApiService::class.java)

}