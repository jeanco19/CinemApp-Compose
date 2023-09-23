package com.jean.cinemappcompose.core.domain.usecase

import com.jean.cinemappcompose.core.util.connectivity.ConnectivityStatus
import kotlinx.coroutines.flow.Flow

interface ConnectivityManagerUseCase {

    fun invoke(): Flow<ConnectivityStatus>

}