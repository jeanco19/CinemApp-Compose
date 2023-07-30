package com.jean.cinemappcompose.presentation.common.component

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.jean.cinemappcompose.R
import com.jean.cinemappcompose.core.ui.theme.font_titan

@Composable
fun AppNameText() {
    Text(
        text = stringResource(id = R.string.welcome_app_name),
        fontSize = 35.sp,
        fontFamily = font_titan,
        color = MaterialTheme.colors.primary
    )
}