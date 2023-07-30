package com.jean.cinemappcompose.core.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType

@Composable
fun FormTextField(
    value: String,
    label: String,
    imeAction: ImeAction = ImeAction.Default,
    onTextChanged: (String) -> Unit
) {

    val focusManager = LocalFocusManager.current

    Column {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = label) },
            value = value,
            onValueChange = { onTextChanged(it) },
            maxLines = 1,
            singleLine = true,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colors.primary,
                focusedLabelColor = MaterialTheme.colors.primary
            ),
            keyboardOptions = KeyboardOptions(
                autoCorrect = false,
                keyboardType = KeyboardType.Text,
                imeAction = imeAction
            ),
            keyboardActions = KeyboardActions(onAny = {
                focusManager.moveFocus(FocusDirection.Next)
            })
        )
    }

}