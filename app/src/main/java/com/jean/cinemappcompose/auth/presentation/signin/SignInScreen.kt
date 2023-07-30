package com.jean.cinemappcompose.auth.presentation.signin

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.jean.cinemappcompose.auth.presentation.signin.component.SignInBottom
import com.jean.cinemappcompose.auth.presentation.signin.component.SignInContent
import com.jean.cinemappcompose.auth.presentation.signin.viewmodel.SignInViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SignInScreen(
    viewModel: SignInViewModel = hiltViewModel(),
    navigateToHome: () -> Unit,
    navigateToSignUp: () -> Unit,
    navigateToRecoverPassword: () -> Unit
) {

    val state = viewModel.uiState
    val context = LocalContext.current

    LaunchedEffect(key1 = viewModel.uiState.isSignedIn) {
        if (viewModel.uiState.isSignedIn) {
            navigateToHome()
        }
    }

    LaunchedEffect(key1 = state.generalError) {
        if (state.generalError != null) {
            Toast.makeText(context, state.generalError, Toast.LENGTH_LONG).show()
        }
    }

    Scaffold(
        content = {
            SignInContent(
                isLoading = state.isLoading,
                isButtonEnabled = state.isButtonEnable,
                state = state,
                onEvent = viewModel::onEvent,
                onRecoverPasswordClicked = { navigateToRecoverPassword() },
            )
        },
        bottomBar = {
            SignInBottom { navigateToSignUp() }
        }
    )

}