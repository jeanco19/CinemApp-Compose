package com.jean.cinemappcompose.presentation.auth.screen

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jean.cinemappcompose.R
import com.jean.cinemappcompose.domain.model.auth.RestartPasswordErrorType
import com.jean.cinemappcompose.presentation.auth.component.EmailTextField
import com.jean.cinemappcompose.presentation.auth.viewmodel.RestartPasswordViewModel
import com.jean.cinemappcompose.presentation.common.component.CustomProgressDialog
import com.jean.cinemappcompose.presentation.common.component.DefaultButton

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RestartPasswordScreen(
    viewModel: RestartPasswordViewModel = hiltViewModel(),
    navigateToSignIn: () -> Unit
) {

    val context = LocalContext.current
    val controller = LocalSoftwareKeyboardController.current

    LaunchedEffect(key1 = viewModel.uiState.isSendEmail) {
        if (viewModel.uiState.isSendEmail) {
            Toast.makeText(context, R.string.restart_password_successfully, Toast.LENGTH_SHORT).show()
            navigateToSignIn()
            viewModel.resetSendEmailValue()
        }
    }

    LaunchedEffect(key1 = viewModel.uiState.errorType) {
        if (viewModel.uiState.errorType.isNotEmpty()) {
            Toast.makeText(
                context,
                handleRestartPasswordErrorType(viewModel.uiState.errorType),
                Toast.LENGTH_LONG
            ).show()
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
                isLoading = viewModel.uiState.isLoading,
                isButtonEnabled = viewModel.email.isNotEmpty(),
                hasEmailError = Pair(
                    viewModel.uiState.hasEmailError,
                    viewModel.uiState.errorType
                ),
                emailValue = viewModel.email,
                onEmailTextChanged = { viewModel.email = it },
                onResetClicked = {
                    controller?.hide()
                    viewModel.resetFieldErrorMessages()
                    viewModel.doRestartPassword()
                }
            )
        }
    )

}

@Composable
fun RestartPasswordContent(
    isLoading: Boolean = false,
    isButtonEnabled: Boolean = false,
    hasEmailError: Pair<Boolean, String>,
    emailValue: String,
    onEmailTextChanged: (String) -> Unit,
    onResetClicked: () -> Unit
) {

    if (isLoading) CustomProgressDialog()

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
        .background(MaterialTheme.colors.background)) {
        Spacer(modifier = Modifier.size(15.dp))
        Text(
            text = stringResource(id = R.string.restart_password_message),
            fontSize = 16.sp,
            color = MaterialTheme.colors.onBackground,
            textAlign = TextAlign.Justify
        )
        Spacer(modifier = Modifier.size(30.dp))
        EmailTextField(
            email = emailValue,
            hasError = hasEmailError.first,
            errorMessage = stringResource(id = handleRestartPasswordErrorType(hasEmailError.second)),
            imeAction = ImeAction.Done,
            onTextChanged = { onEmailTextChanged(it) }
        )
        Spacer(modifier = Modifier.size(50.dp))
        DefaultButton(
            label = stringResource(id = R.string.send_button_text),
            isButtonEnabled = isButtonEnabled,
            onButtonClicked = { onResetClicked() }
        )
    }

}

fun handleRestartPasswordErrorType(errorType: String): Int {
    return when (errorType) {
        RestartPasswordErrorType.RESTART_PASSWORD_ERROR.name -> R.string.error_restart_password
        RestartPasswordErrorType.EMAIL_INVALID_PATTERN.name -> R.string.error_email_invalid_pattern
        RestartPasswordErrorType.USER_NOT_FOUND.name -> R.string.error_user_not_found
        else -> R.string.error_restart_password
    }
}
