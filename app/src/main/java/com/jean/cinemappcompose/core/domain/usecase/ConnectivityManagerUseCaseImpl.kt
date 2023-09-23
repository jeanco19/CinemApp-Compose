package com.jean.cinemappcompose.core.domain.usecase

import com.jean.cinemappcompose.core.util.connectivity.ConnectivityStatus
import com.jean.cinemappcompose.core.util.connectivity.NetworkConnectivityObserver
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ConnectivityManagerUseCaseImpl @Inject constructor(
    private val networkConnectivity: NetworkConnectivityObserver
) : ConnectivityManagerUseCase {
    override fun invoke(): Flow<ConnectivityStatus> {
        return networkConnectivity.observe()
    }

}