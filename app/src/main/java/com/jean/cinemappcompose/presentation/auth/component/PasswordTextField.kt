package com.jean.cinemappcompose.presentation.auth.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.sp
import com.jean.cinemappcompose.R

@Composable
fun PasswordTextField(
    password: String,
    hasError: Boolean = false,
    errorMessage: String = "",
    imeAction: ImeAction = ImeAction.Default,
    onTextChanged: (String) -> Unit
) {

    var passwordVisibility by remember { mutableStateOf(false) }

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = stringResource(id = R.string.password_field_placeholder)) },
        value = password,
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
            keyboardType = KeyboardType.Password,
            imeAction = imeAction
        ),
        trailingIcon = {
            val image = if (passwordVisibility) Icons.Filled.Visibility
            else Icons.Filled.VisibilityOff
            IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                Icon(
                    imageVector = image,
                    contentDescription = stringResource(id = R.string.show_password_image_description)
                )
            }
        },
        visualTransformation = if (passwordVisibility) VisualTransformation.None
        else PasswordVisualTransformation()
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