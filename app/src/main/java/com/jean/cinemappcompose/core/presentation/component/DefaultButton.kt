package com.jean.cinemappcompose.core.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.*
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
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            disabledContainerColor = Color.LightGray,
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