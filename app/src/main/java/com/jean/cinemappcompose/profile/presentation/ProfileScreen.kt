package com.jean.cinemappcompose.profile.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jean.cinemappcompose.core.presentation.component.DefaultButton
import com.jean.cinemappcompose.profile.presentation.viewmodel.ProfileViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel(),
    navigateToSignIn: () -> Unit
) {
    Scaffold(
        content = { padding ->
            Column(modifier = Modifier.padding(padding)) {
                Text(text = "Profile Screen")
                Spacer(modifier = Modifier.height(30.dp))
                DefaultButton(
                    label = "Cerrar Sesión",
                    onButtonClicked = {
                        viewModel.doSignOut()
                        navigateToSignIn()
                    }
                )
            }
        }
    )
}