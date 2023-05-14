package com.jean.cinemappcompose.presentation.auth.viewmodel

import androidx.lifecycle.ViewModel
import com.jean.cinemappcompose.domain.usecase.auth.SignInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase
) : ViewModel() {

}