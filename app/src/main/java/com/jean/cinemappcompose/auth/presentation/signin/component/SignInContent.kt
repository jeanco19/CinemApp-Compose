package com.jean.cinemappcompose.auth.presentation.signin.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jean.cinemappcompose.R
import com.jean.cinemappcompose.auth.presentation.signin.viewmodel.SignInEvent
import com.jean.cinemappcompose.auth.presentation.signin.viewmodel.SignInUiState
import com.jean.cinemappcompose.core.presentation.component.EmailTextField
import com.jean.cinemappcompose.core.presentation.component.PasswordTextField
import com.jean.cinemappcompose.core.presentation.component.AppNameText
import com.jean.cinemappcompose.core.presentation.component.CustomProgressDialog
import com.jean.cinemappcompose.core.presentation.component.DefaultButton

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SignInContent(
    isLoading: Boolean = false,
    isButtonEnabled: Boolean = false,
    state: SignInUiState,
    onEvent: (SignInEvent) -> Unit,
    onRecoverPasswordClicked: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.background)
    ) {

        val controller = LocalSoftwareKeyboardController.current
        if (isLoading) CustomProgressDialog()

        Spacer(modifier = Modifier.size(30.dp))
        AppNameText()
        Spacer(modifier = Modifier.size(30.dp))
        EmailTextField(
            email = state.email,
            imeAction = ImeAction.Next,
            errorMessage = state.emailError,
            onTextChanged = { onEvent(SignInEvent.EmailChange(it)) }
        )
        Spacer(modifier = Modifier.size(10.dp))
        PasswordTextField(
            password = state.password,
            errorMessage = state.passwordError,
            imeAction = ImeAction.Done,
            onTextChanged = { onEvent(SignInEvent.PasswordChange(it)) }
        )
        Text(
            modifier = Modifier
                .padding(top = 10.dp)
                .clickable { onRecoverPasswordClicked() },
            text = stringResource(id = R.string.forgot_your_password_text),
            fontSize = 15.sp,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.size(40.dp))
        DefaultButton(
            label = stringResource(id = R.string.sign_in_button_text),
            isButtonEnabled = isButtonEnabled,
            onButtonClicked = {
                controller?.hide()
                onEvent(SignInEvent.SignIn)
            }
        )
    }
}