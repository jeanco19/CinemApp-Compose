package com.jean.cinemappcompose.auth.presentation.welcome.component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jean.cinemappcompose.R
import com.jean.cinemappcompose.core.ui.theme.font_baloo
import com.jean.cinemappcompose.core.presentation.component.AppNameText

@Composable
fun WelcomeContent() {
    AppNameText()
    Spacer(modifier = Modifier.padding(top = 30.dp))
    Text(
        text = stringResource(id = R.string.welcome_title),
        fontSize = 24.sp,
        fontFamily = font_baloo,
        color = MaterialTheme.colorScheme.onBackground
    )
    Spacer(modifier = Modifier.padding(top = 20.dp))
    Text(
        text = stringResource(id = R.string.welcome_description),
        fontSize = 20.sp,
        fontFamily = font_baloo,
        color = MaterialTheme.colorScheme.onBackground
    )
}