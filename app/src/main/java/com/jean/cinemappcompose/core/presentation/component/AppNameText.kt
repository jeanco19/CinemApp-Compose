package com.jean.cinemappcompose.core.presentation.component

import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.jean.cinemappcompose.R
import com.jean.cinemappcompose.core.ui.theme.font_titan

@Composable
fun AppNameText(
    modifier: Modifier = Modifier,
    size: TextUnit = 35.sp,
    color: Color = MaterialTheme.colorScheme.primary
) {
    Text(
        modifier = modifier,
        text = stringResource(id = R.string.welcome_app_name),
        fontSize = size,
        fontFamily = font_titan,
        color = color
    )
}