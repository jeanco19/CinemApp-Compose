package com.jean.cinemappcompose.auth.presentation.signin.viewmodel

interface SignInEvent {

    data class EmailChange(val email: String) : SignInEvent
    data class PasswordChange(val password: String) : SignInEvent
    object SignIn : SignInEvent

}