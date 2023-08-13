package com.jean.cinemappcompose.auth.presentation.restart_password.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jean.cinemappcompose.R
import com.jean.cinemappcompose.auth.presentation.restart_password.viewmodel.RestartPasswordEvent
import com.jean.cinemappcompose.auth.presentation.restart_password.viewmodel.RestartPasswordUiState
import com.jean.cinemappcompose.core.presentation.component.EmailTextField
import com.jean.cinemappcompose.core.presentation.component.CustomProgressDialog
import com.jean.cinemappcompose.core.presentation.component.DefaultButton

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun RestartPasswordContent(
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    isButtonEnabled: Boolean = false,
    state: RestartPasswordUiState,
    onEvent: (RestartPasswordEvent) -> Unit
) {

    val controller = LocalSoftwareKeyboardController.current
    if (isLoading) CustomProgressDialog()

    Column(modifier = modifier
        .fillMaxSize()
        .padding(16.dp)
        .background(MaterialTheme.colorScheme.background)) {
        Spacer(modifier = Modifier.size(15.dp))
        Text(
            text = stringResource(id = R.string.restart_password_message),
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Justify
        )
        Spacer(modifier = Modifier.size(30.dp))
        EmailTextField(
            email = state.email,
            errorMessage = state.emailError,
            imeAction = ImeAction.Done,
            onTextChanged = { onEvent(RestartPasswordEvent.EmailChange(it)) }
        )
        Spacer(modifier = Modifier.size(50.dp))
        DefaultButton(
            label = stringResource(id = R.string.send_button_text),
            isButtonEnabled = isButtonEnabled,
            onButtonClicked = {
                controller?.hide()
                onEvent(RestartPasswordEvent.RestartPassword)
            }
        )
    }

}