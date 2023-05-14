package com.jean.cinemappcompose.presentation.auth.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jean.cinemappcompose.R
import com.jean.cinemappcompose.presentation.util.EmailTextField
import com.jean.cinemappcompose.presentation.util.SingleButton

private const val EMPTY_STRING = ""

@Composable
fun RecoverPasswordScreen() {
    var email by remember { mutableStateOf(EMPTY_STRING) }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
        .background(MaterialTheme.colors.background)) {
        Text(
            text = stringResource(id = R.string.recover_password_title),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.primary
        )
        Spacer(modifier = Modifier.size(40.dp))
        Text(
            text = stringResource(id = R.string.recover_password_message),
            fontSize = 16.sp,
            color = MaterialTheme.colors.onBackground,
            textAlign = TextAlign.Justify
        )
        Spacer(modifier = Modifier.size(30.dp))
        EmailTextField(
            email = email,
            onTextChanged = { email = it }
        )
        Spacer(modifier = Modifier.size(50.dp))
        SingleButton(label = stringResource(id = R.string.send_button_text)) {
            // TODO Llamar función de recuperar contraseña en el ViewModel
        }
    }
}