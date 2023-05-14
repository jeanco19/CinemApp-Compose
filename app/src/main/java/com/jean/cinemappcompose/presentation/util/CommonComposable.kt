package com.jean.cinemappcompose.presentation.util

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.jean.cinemappcompose.R
import com.jean.cinemappcompose.ui.theme.font_titan

@Composable
fun AppNameTitle() {
    Text(
        text = stringResource(id = R.string.welcome_app_name),
        fontSize = 35.sp,
        fontFamily = font_titan,
        color = MaterialTheme.colors.primary
    )
}

@Composable
fun EmailTextField(
    email: String,
    hasError: Boolean = false,
    errorMessage: String = "",
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
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
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

@Composable
fun PasswordTextField(
    password: String,
    hasError: Boolean = false,
    errorMessage: String = "",
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
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
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

@Composable
fun SingleButton(
    modifier: Modifier = Modifier,
    label: String,
    isButtonEnabled: Boolean = true,
    onButtonClicked: () -> Unit
) {

    val buttonModifier = modifier
        .fillMaxWidth()
        .height(45.dp)

    Button(
        modifier = buttonModifier,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = MaterialTheme.colors.onPrimary,
            disabledBackgroundColor = Color.LightGray,
            disabledContentColor = Color.White
        ),
        enabled = isButtonEnabled,
        onClick = { onButtonClicked() }
    ) {
        Text(
            text = label.uppercase(),
            fontSize = 16.sp
        )
    }
}

@Composable
fun CustomProgressDialog() {
    Dialog(onDismissRequest = { }, DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)) {
        Box(
            modifier = Modifier
            .size(100.dp)
            .background(Color.White, shape = RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }
}