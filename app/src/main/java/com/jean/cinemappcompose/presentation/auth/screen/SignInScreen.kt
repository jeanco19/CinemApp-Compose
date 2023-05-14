package com.jean.cinemappcompose.presentation.auth.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jean.cinemappcompose.R
import com.jean.cinemappcompose.presentation.util.AppNameTitle
import com.jean.cinemappcompose.presentation.util.EmailTextField
import com.jean.cinemappcompose.presentation.util.PasswordTextField
import com.jean.cinemappcompose.presentation.util.SingleButton

private const val EMPTY_STRING = ""

@Composable
fun SignInScreen(
    signInClicked: () -> Unit,
    signUpClicked: () -> Unit,
    recoverPasswordClicked: () -> Unit
) {

    var email by remember { mutableStateOf(EMPTY_STRING) }
    var password by remember { mutableStateOf(EMPTY_STRING) }
    val isLoginEnabled by remember { mutableStateOf(false) }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
        .background(MaterialTheme.colors.background)) {
        Spacer(modifier = Modifier.size(30.dp))
        AppNameTitle()
        Spacer(modifier = Modifier.size(30.dp))
        EmailTextField(
            email = email,
            onTextChanged = { email = it }
        )
        Spacer(modifier = Modifier.size(10.dp))
        PasswordTextField(
            password = password,
            onTextChanged = { password = it }
        )
        Text(
            modifier = Modifier
                .padding(top = 10.dp)
                .clickable { recoverPasswordClicked() },
            text = stringResource(id = R.string.forgot_your_password_text),
            fontSize = 15.sp,
            color = MaterialTheme.colors.primary
        )
        Spacer(modifier = Modifier.size(40.dp))
        SingleButton(
            label = stringResource(id = R.string.sign_in_button_text),
            isButtonEnabled = isLoginEnabled,
            onButtonClicked = { signInClicked() }
        )
        SignUpOption { signUpClicked() }
    }
}

@Composable
fun SignUpOption(onTextPressed: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Bottom
    ) {
        Text(
            text = stringResource(id = R.string.still_dont_have_account_text),
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