package com.jean.cinemappcompose.core.util.connectivity

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeNetworkConnectivityObserver : ConnectivityObserver {

    override fun observe(): Flow<ConnectivityStatus> {
        return flow {
            emit(ConnectivityStatus.AVAILABLE)
        }
    }

}