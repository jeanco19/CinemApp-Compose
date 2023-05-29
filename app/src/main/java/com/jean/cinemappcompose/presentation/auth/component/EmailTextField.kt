package com.jean.cinemappcompose.presentation.auth.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import com.jean.cinemappcompose.R

@Composable
fun EmailTextField(
    email: String,
    hasError: Boolean = false,
    errorMessage: String = "",
    imeAction: ImeAction = ImeAction.Default,
    onTextChanged: (String) -> Unit
) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = stringResource(id = R.string.email_field_placeholder)) },
        value = email,
        onValueChange = { onTextChanged(it) },
        maxLines = 1,
        singleLine = true,
        isError = hasError,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = MaterialTheme.colors.primary,
            focusedLabelColor = MaterialTheme.colors.primary,
            errorBorderColor = MaterialTheme.colors.error
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = imeAction
        )
    )
    if (hasError) {
        Text(
            text = errorMessage,
            color = MaterialTheme.colors.error,
            fontSize = 12.sp,
            maxLines = 1
        )
    }
}