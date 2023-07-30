package com.jean.cinemappcompose.auth.presentation.signup.viewmodel

interface SignUpEvent {

    data class NameChange(val name: String) : SignUpEvent
    data class LastName(val lastName: String) : SignUpEvent
    data class EmailChange(val email: String) : SignUpEvent
    data class PasswordChange(val password: String) : SignUpEvent
    object SignUp : SignUpEvent

}