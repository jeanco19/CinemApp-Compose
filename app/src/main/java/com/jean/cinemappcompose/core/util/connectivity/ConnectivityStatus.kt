package com.jean.cinemappcompose.core.util.connectivity

import com.jean.cinemappcompose.R

enum class ConnectivityStatus(val message: Int) {
    AVAILABLE(R.string.available_connectivity_message),
    UNAVAILABLE(R.string.unavailable_connectivity_message),
    LOSING(R.string.losing_connectivity_message),
    LOST(R.string.lost_connectivity_message)
}