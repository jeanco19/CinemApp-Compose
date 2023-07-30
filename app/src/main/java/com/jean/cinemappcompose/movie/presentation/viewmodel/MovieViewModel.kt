package com.jean.cinemappcompose.movie.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jean.cinemappcompose.auth.domain.usecase.signout.SignOutUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val signOutUseCase: SignOutUseCase
): ViewModel() {

    fun doSignOut() {
        viewModelScope.launch {
            signOutUseCase.invoke()
        }
    }

}