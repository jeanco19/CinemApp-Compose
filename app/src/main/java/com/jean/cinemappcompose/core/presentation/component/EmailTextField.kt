package com.jean.cinemappcompose.core.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import com.jean.cinemappcompose.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailTextField(
    email: String,
    errorMessage: Int? = null,
    imeAction: ImeAction = ImeAction.Default,
    onTextChanged: (String) -> Unit
) {

    val focusManager = LocalFocusManager.current

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = stringResource(id = R.string.email_field_placeholder)) },
        value = email,
        onValueChange = { onTextChanged(it) },
        maxLines = 1,
        singleLine = true,
        isError = errorMessage != null,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = MaterialTheme.colorScheme.primary,
            focusedLabelColor = MaterialTheme.colorScheme.primary,
            errorBorderColor = MaterialTheme.colorScheme.error
        ),
        keyboardOptions = KeyboardOptions(
            autoCorrect = false,
            keyboardType = KeyboardType.Email,
            imeAction = imeAction
        ),
        keyboardActions = KeyboardActions(onAny = {
            focusManager.moveFocus(FocusDirection.Next)
        })
    )

    if (errorMessage != null) {
        Text(
            text = stringResource(id = errorMessage),
            color = MaterialTheme.colorScheme.error,
            fontSize = 12.sp,
            maxLines = 1
        )
    }

}