package com.jean.cinemappcompose.presentation.common.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DefaultButton(
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