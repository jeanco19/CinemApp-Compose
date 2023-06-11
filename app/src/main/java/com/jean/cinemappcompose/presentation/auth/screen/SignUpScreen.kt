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
import com.jean.cinemappcompose.domain.model.auth.SignUpErrorType
import com.jean.cinemappcompose.presentation.auth.component.EmailTextField
import com.jean.cinemappcompose.presentation.auth.component.FormTextField
import com.jean.cinemappcompose.presentation.auth.component.PasswordTextField
import com.jean.cinemappcompose.presentation.auth.viewmodel.SignUpViewModel
import com.jean.cinemappcompose.presentation.common.component.AppNameText
import com.jean.cinemappcompose.presentation.common.component.CustomProgressDialog
import com.jean.cinemappcompose.presentation.common.component.DefaultButton

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SignUpScreen(
    viewModel: SignUpViewModel = hiltViewModel(),
    navigateToSignIn: () -> Unit
) {

    val context = LocalContext.current
    val controller = LocalSoftwareKeyboardController.current

    LaunchedEffect(key1 = viewModel.uiState.isSignedUp) {
        if (viewModel.uiState.isSignedUp) {
            Toast.makeText(context, R.string.sign_up_successfully, Toast.LENGTH_SHORT).show()
            navigateToSignIn()
            viewModel.resetFieldSignedUp()
        }
    }

    if (viewModel.uiState.errorType.isNotEmpty() &&
        !viewModel.uiState.hasEmailError &&
        !viewModel.uiState.hasPasswordError) {
        Toast.makeText(context, handleSignUpErrorType(viewModel.uiState.errorType), Toast.LENGTH_LONG).show()
        viewModel.resetFieldErrorMessages()
    }

    Scaffold(
        content = {
            SignUpContent(
                isLoading = viewModel.uiState.isLoading,
                isButtonEnabled = viewModel.isButtonEnable,
                hasEmailError = Pair(
                    viewModel.uiState.hasEmailError,
                    viewModel.uiState.errorType
                ),
                hasPasswordError = Pair(
                    viewModel.uiState.hasPasswordError,
                    viewModel.uiState.errorType
                ),
                nameValue = viewModel.name,
                lastNameValue = viewModel.lastName,
                emailValue = viewModel.email,
                passwordValue = viewModel.password,
                onNameTextChanged = {
                    viewModel.name = it
                    viewModel.handleButtonEnable()
                },
                onLastNameTextChanged = {
                    viewModel.lastName = it
                    viewModel.handleButtonEnable()
                },
                onEmailTextChanged = {
                    viewModel.email = it
                    viewModel.handleButtonEnable()
                },
                onPasswordTextChanged = {
                    viewModel.password = it
                    viewModel.handleButtonEnable()
                },
                onSignUpClicked = {
                    controller?.hide()
                    viewModel.resetFieldErrorMessages()
                    viewModel.doSignUp()
                }
            )
        },
        bottomBar = {
            SignUpBottom(onTextPressed = { navigateToSignIn() })
        }
    )
}

@Composable
fun SignUpContent(
    isLoading: Boolean = false,
    isButtonEnabled: Boolean = false,
    hasEmailError: Pair<Boolean, String>,
    hasPasswordError: Pair<Boolean, String>,
    nameValue: String,
    lastNameValue: String,
    emailValue: String,
    passwordValue: String,
    onNameTextChanged: (String) -> Unit,
    onLastNameTextChanged: (String) -> Unit,
    onEmailTextChanged: (String) -> Unit,
    onPasswordTextChanged: (String) -> Unit,
    onSignUpClicked: () -> Unit
) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
        .background(MaterialTheme.colors.background)) {

        if (isLoading) CustomProgressDialog()

        Spacer(modifier = Modifier.size(30.dp))
        AppNameText()
        Spacer(modifier = Modifier.size(30.dp))
        FormTextField(
            value = nameValue,
            label = stringResource(id = R.string.name_field_placeholder),
            imeAction = ImeAction.Next,
            onTextChanged = { onNameTextChanged(it) }
        )
        Spacer(modifier = Modifier.size(10.dp))
        FormTextField(
            value = lastNameValue,
            label = stringResource(id = R.string.last_name_field_placeholder),
            imeAction = ImeAction.Next,
            onTextChanged = { onLastNameTextChanged(it) }
        )
        Spacer(modifier = Modifier.size(10.dp))
        EmailTextField(
            email = emailValue,
            hasError = hasEmailError.first,
            errorMessage = stringResource(id = handleSignUpErrorType(hasEmailError.second)),
            imeAction = ImeAction.Next,
            onTextChanged = { onEmailTextChanged(it) }
        )
        Spacer(modifier = Modifier.size(10.dp))
        PasswordTextField(
            password = passwordValue,
            hasError = hasPasswordError.first,
            errorMessage = stringResource(id = handleSignUpErrorType(hasPasswordError.second)),
            imeAction = ImeAction.Done,
            onTextChanged = { onPasswordTextChanged(it) }
        )
        Spacer(modifier = Modifier.size(50.dp))
        DefaultButton(
            label = stringResource(id = R.string.sign_up_button_text),
            isButtonEnabled = isButtonEnabled,
            onButtonClicked = { onSignUpClicked() }
        )

    }
}

@Composable
fun SignUpBottom(onTextPressed: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Bottom
    ) {
        Text(
            text = stringResource(id = R.string.already_have_an_account),
            fontSize = 16.sp,
            color = Color.Gray
        )
        Text(
            modifier = Modifier
                .padding(start = 1.dp)
                .clickable { onTextPressed() },
            text = stringResource(id = R.string.sign_in_here_text),
            fontSize = 16.sp,
            color = MaterialTheme.colors.primary
        )
    }
}

@StringRes
private fun handleSignUpErrorType(errorType: String): Int {
    return when(errorType) {
        SignUpErrorType.SIGN_UP_ERROR.name -> R.string.error_sign_up
        SignUpErrorType.EMAIL_INVALID_PATTERN.name -> R.string.error_email_invalid_pattern
        SignUpErrorType.EMAIL_ALREADY_IN_USE.name -> R.string.error_email_already_in_use
        SignUpErrorType.PASSWORD_INVALID_LENGTH.name -> R.string.error_password_invalid_length
        else -> R.string.error_sign_up
    }
}