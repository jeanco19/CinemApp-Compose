package com.jean.cinemappcompose.auth.presentation.signup.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.jean.cinemappcompose.R
import com.jean.cinemappcompose.core.presentation.component.FormTextField
import com.jean.cinemappcompose.auth.presentation.signup.viewmodel.SignUpEvent
import com.jean.cinemappcompose.auth.presentation.signup.viewmodel.SignUpUiState
import com.jean.cinemappcompose.core.presentation.component.EmailTextField
import com.jean.cinemappcompose.core.presentation.component.PasswordTextField
import com.jean.cinemappcompose.presentation.common.component.AppNameText
import com.jean.cinemappcompose.presentation.common.component.CustomProgressDialog
import com.jean.cinemappcompose.presentation.common.component.DefaultButton

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SignUpContent(
    isLoading: Boolean = false,
    state: SignUpUiState,
    onEvent: (SignUpEvent) -> Unit
) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
        .background(MaterialTheme.colors.background)) {

        val controller = LocalSoftwareKeyboardController.current
        if (isLoading) CustomProgressDialog()

        Spacer(modifier = Modifier.size(30.dp))
        AppNameText()
        Spacer(modifier = Modifier.size(30.dp))
        FormTextField(
            value = state.name,
            label = stringResource(id = R.string.name_field_placeholder),
            imeAction = ImeAction.Next,
            onTextChanged = { onEvent(SignUpEvent.NameChange(it)) }
        )
        Spacer(modifier = Modifier.size(10.dp))
        FormTextField(
            value = state.lastName,
            label = stringResource(id = R.string.last_name_field_placeholder),
            imeAction = ImeAction.Next,
            onTextChanged = { onEvent(SignUpEvent.LastName(it)) }
        )
        Spacer(modifier = Modifier.size(10.dp))
        EmailTextField(
            email = state.email,
            errorMessage = state.emailError,
            imeAction = ImeAction.Next,
            onTextChanged = { onEvent(SignUpEvent.EmailChange(it)) }
        )
        Spacer(modifier = Modifier.size(10.dp))
        PasswordTextField(
            password = state.password,
            errorMessage = state.passwordError,
            imeAction = ImeAction.Done,
            onTextChanged = { onEvent(SignUpEvent.PasswordChange(it)) }
        )
        Spacer(modifier = Modifier.size(50.dp))
        DefaultButton(
            label = stringResource(id = R.string.sign_up_button_text),
            isButtonEnabled = state.isButtonEnable,
            onButtonClicked = {
                controller?.hide()
                onEvent(SignUpEvent.SignUp)
            }
        )

    }
}