package com.jean.cinemappcompose.auth.presentation.signup

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.jean.cinemappcompose.R
import com.jean.cinemappcompose.auth.presentation.signup.component.SignUpBottom
import com.jean.cinemappcompose.auth.presentation.signup.component.SignUpContent
import com.jean.cinemappcompose.auth.presentation.signup.viewmodel.SignUpViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel = hiltViewModel(),
    navigateToSignIn: () -> Unit
) {

    val state = viewModel.uiState
    val context = LocalContext.current
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = viewModel.uiState.isSignedUp) {
        if (viewModel.uiState.isSignedUp) {
            Toast.makeText(context, R.string.sign_up_successfully, Toast.LENGTH_SHORT).show()
            navigateToSignIn()
        }
    }

    LaunchedEffect(key1 = state.generalError) {
        if (state.generalError != null) {
            Toast.makeText(context, state.generalError, Toast.LENGTH_LONG).show()
        }
    }

    LaunchedEffect(key1 = state.hasConnectivity) {
        if (state.connectivityMessage != null) {
            snackBarHostState.showSnackbar(
                message = context.getString(state.connectivityMessage),
                duration = SnackbarDuration.Short
            )
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) }
    ) {
        SignUpContent(
            isLoading = state.isLoading,
            state = state,
            onEvent = viewModel::onEvent
        )
        SignUpBottom(onTextPressed = { navigateToSignIn() })
    }
}

