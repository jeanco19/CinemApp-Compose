package com.jean.cinemappcompose.presentation.auth.screen

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jean.cinemappcompose.R
import com.jean.cinemappcompose.domain.model.auth.SignInErrorType
import com.jean.cinemappcompose.presentation.auth.component.EmailTextField
import com.jean.cinemappcompose.presentation.auth.component.PasswordTextField
import com.jean.cinemappcompose.presentation.auth.viewmodel.SignInViewModel
import com.jean.cinemappcompose.presentation.common.component.AppNameText
import com.jean.cinemappcompose.presentation.common.component.CustomProgressDialog
import com.jean.cinemappcompose.presentation.common.component.DefaultButton

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SignInScreen(
    viewModel: SignInViewModel = hiltViewModel(),
    navigateToMovies: () -> Unit,
    navigateToSignUp: () -> Unit,
    navigateToRecoverPassword: () -> Unit
) {

    viewModel.validateSession()
    val context = LocalContext.current
    val controller = LocalSoftwareKeyboardController.current

    LaunchedEffect(key1 = viewModel.uiState.isSignedIn) {
        if (viewModel.uiState.isSignedIn) {
            navigateToMovies()
        }
    }

    if (viewModel.uiState.errorType.isNotEmpty() &&
        !viewModel.uiState.hasEmailError &&
        !viewModel.uiState.hasPasswordError) {
        Toast.makeText(context, handleSignInErrorType(viewModel.uiState.errorType), Toast.LENGTH_LONG).show()
        viewModel.resetFieldErrorMessages()
    }

    Scaffold(
        content = {
            SignInContent(
                isLoading = viewModel.uiState.isLoading,
                isButtonEnabled = viewModel.isButtonEnable,
                emailValue = viewModel.email,
                passwordValue = viewModel.password,
                hasEmailError = Pair(
                    viewModel.uiState.hasEmailError,
                    viewModel.uiState.errorType
                ),
                hasPasswordError = Pair(
                    viewModel.uiState.hasPasswordError,
                    viewModel.uiState.errorType
                ),
                onEmailTextChanged = {
                    viewModel.email = it
                    viewModel.handleButtonEnable()
                },
                onPasswordTextChanged = {
                    viewModel.password = it
                    viewModel.handleButtonEnable()
                },
                onRecoverPasswordClicked = { navigateToRecoverPassword() },
                onSignInClicked = {
                    controller?.hide()
                    viewModel.resetFieldErrorMessages()
                    viewModel.doSignIn()
                }
            )
        },
        bottomBar = {
            SignInBottom { navigateToSignUp() }
        }
    )

}

@Composable
fun SignInContent(
    isLoading: Boolean = false,
    isButtonEnabled: Boolean = false,
    emailValue: String,
    passwordValue: String,
    hasEmailError: Pair<Boolean, String>,
    hasPasswordError: Pair<Boolean, String>,
    onEmailTextChanged: (String) -> Unit,
    onPasswordTextChanged: (String) -> Unit,
    onRecoverPasswordClicked: () -> Unit,
    onSignInClicked: () -> Unit
) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
        .background(MaterialTheme.colors.background)) {

        if (isLoading) CustomProgressDialog()

        Spacer(modifier = Modifier.size(30.dp))
        AppNameText()
        Spacer(modifier = Modifier.size(30.dp))
        EmailTextField(
            email = emailValue,
            hasError = hasEmailError.first,
            imeAction = ImeAction.Next,
            errorMessage = stringResource(id = handleSignInErrorType(hasEmailError.second)),
            onTextChanged = { onEmailTextChanged(it) }
        )
        Spacer(modifier = Modifier.size(10.dp))
        PasswordTextField(
            password = passwordValue,
            hasError = hasPasswordError.first,
            errorMessage = stringResource(id = handleSignInErrorType(hasPasswordError.second)),
            imeAction = ImeAction.Done,
            onTextChanged = { onPasswordTextChanged(it) }
        )
        Text(
            modifier = Modifier
                .padding(top = 10.dp)
                .clickable { onRecoverPasswordClicked() },
            text = stringResource(id = R.string.forgot_your_password_text),
            fontSize = 15.sp,
            color = MaterialTheme.colors.primary
        )
        Spacer(modifier = Modifier.size(40.dp))
        DefaultButton(
            label = stringResource(id = R.string.sign_in_button_text),
            isButtonEnabled = isButtonEnabled,
            onButtonClicked = { onSignInClicked() }
        )
    }
}

@Composable
fun SignInBottom(onTextPressed: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Bottom
    ) {
        Text(
            text = stringResource(id = R.string.still_do_not_have_account_text),
            fontSize = 15.sp,
            color = Color.Gray
        )
        Text(
            modifier = Modifier
                .padding(start = 1.dp)
                .clickable { onTextPressed() },
            text = stringResource(id = R.string.sign_up_here_text),
            fontSize = 15.sp,
            color = MaterialTheme.colors.primary
        )
    }
}

@StringRes
private fun handleSignInErrorType(errorType: String): Int {
    return when (errorType) {
        SignInErrorType.SIGN_IN_ERROR.name -> R.string.error_sign_in
        SignInErrorType.EMAIL_INVALID_PATTERN.name -> R.string.error_email_invalid_pattern
        SignInErrorType.USER_NOT_FOUND.name -> R.string.error_user_not_found
        SignInErrorType.PASSWORD_INVALID_LENGTH.name -> R.string.error_password_invalid_length
        SignInErrorType.PASSWORD_WRONG.name -> R.string.error_password_wrong
        else -> R.string.error_sign_in
    }
}