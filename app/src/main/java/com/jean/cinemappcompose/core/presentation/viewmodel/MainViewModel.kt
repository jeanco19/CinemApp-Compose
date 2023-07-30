package com.jean.cinemappcompose.core.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.jean.cinemappcompose.profile.domain.usecase.GetCurrentUserIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    getCurrentUserIdUseCase: GetCurrentUserIdUseCase
) : ViewModel() {

    val currentUserId = getCurrentUserIdUseCase.currentUserID

}