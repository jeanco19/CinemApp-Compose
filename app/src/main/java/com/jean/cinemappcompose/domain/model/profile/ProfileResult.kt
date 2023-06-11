package com.jean.cinemappcompose.domain.model.profile

sealed class ProfileResult {
    object Error : ProfileResult()
    class Success(val isRegistered: Boolean = false, val user: User? = null) : ProfileResult()
}