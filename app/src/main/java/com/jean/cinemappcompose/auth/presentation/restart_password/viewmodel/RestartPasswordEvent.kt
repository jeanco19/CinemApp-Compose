package com.jean.cinemappcompose.auth.presentation.restart_password.viewmodel

interface RestartPasswordEvent {

    data class EmailChange(val email: String) : RestartPasswordEvent
    object RestartPassword : RestartPasswordEvent

}