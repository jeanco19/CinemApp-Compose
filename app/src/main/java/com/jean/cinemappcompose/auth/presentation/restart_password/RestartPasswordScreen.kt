package com.jean.cinemappcompose.auth.presentation.restart_password

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jean.cinemappcompose.R
import com.jean.cinemappcompose.auth.presentation.restart_password.component.RestartPasswordContent
import com.jean.cinemappcompose.auth.presentation.restart_password.viewmodel.RestartPasswordViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun RestartPasswordScreen(
    viewModel: RestartPasswordViewModel = hiltViewModel(),
    navigateToSignIn: () -> Unit
) {

    val state = viewModel.uiState
    val context = LocalContext.current

    LaunchedEffect(state.isSendEmail) {
        if (state.isSendEmail) {
            Toast.makeText(
                context,
                R.string.restart_password_successfully,
                Toast.LENGTH_SHORT
            ).show()
            navigateToSignIn()
        }
    }

    LaunchedEffect(state.generalError) {
        if (state.generalError != null) {
            Toast.makeText(context, state.generalError, Toast.LENGTH_LONG).show()
        }
    }

    Scaffold(
        topBar = {
            Text(
                modifier = Modifier.padding(top = 5.dp, start = 10.dp),
                text = stringResource(id = R.string.restart_password_title),
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.primary
            )
        },
        content = {
            RestartPasswordContent(
                isLoading = state.isLoading,
                isButtonEnabled = state.isButtonEnable,
                state = state,
                onEvent = viewModel::onEvent
            )
        }
    )

}