package com.jean.cinemappcompose.auth.presentation.welcome

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jean.cinemappcompose.auth.presentation.signin.viewmodel.SignInViewModel
import com.jean.cinemappcompose.auth.presentation.welcome.component.WelcomeBottom
import com.jean.cinemappcompose.auth.presentation.welcome.component.WelcomeContent

@Composable
fun WelcomeScreen(
    viewModel: SignInViewModel = hiltViewModel(),
    signInClicked: () -> Unit,
    signUpClicked: () -> Unit,
    navigateToHome: () -> Unit
) {

    LaunchedEffect(key1 = viewModel.uiState.isSignedIn) {
        if (viewModel.uiState.isSignedIn) {
            navigateToHome()
        }
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
        .background(color = MaterialTheme.colorScheme.background)) {
        Spacer(modifier = Modifier.size(40.dp))
        WelcomeContent()
        WelcomeBottom(
            signInClicked = { signInClicked() },
            signUpClicked = { signUpClicked() }
        )
    }

}