package com.jean.cinemappcompose.core.di

import com.jean.cinemappcompose.core.domain.usecase.ConnectivityManagerUseCase
import com.jean.cinemappcompose.core.domain.usecase.ConnectivityManagerUseCaseImpl
import com.jean.cinemappcompose.core.util.connectivity.NetworkConnectivityObserver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    fun provideNetworkManagerUseCase(
        networkConnectivityObserver: NetworkConnectivityObserver
    ): ConnectivityManagerUseCase {
        return ConnectivityManagerUseCaseImpl(networkConnectivityObserver)
    }

}