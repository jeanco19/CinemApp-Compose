package com.jean.cinemappcompose.core.di

import com.jean.cinemappcompose.core.data.network.MoviesApiService
import com.jean.cinemappcompose.core.data.network.interceptor.ApiKeyInterceptor
import com.jean.cinemappcompose.core.data.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideApiKeyInterceptor(): ApiKeyInterceptor = ApiKeyInterceptor()

    @Singleton
    @Provides
    fun provideOkHttpClient(apiKeyInterceptor: ApiKeyInterceptor): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(apiKeyInterceptor).build()
    }

    @Singleton
    @Provides
    fun provideRetrofitService(okHttpClient: OkHttpClient): MoviesApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(MoviesApiService::class.java)
    }

// ----- COROUTINE INSTANCE ----- //

    @Provides
    fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO

}