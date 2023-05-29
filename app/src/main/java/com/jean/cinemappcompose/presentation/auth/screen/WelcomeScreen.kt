package com.jean.cinemappcompose.presentation.auth.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jean.cinemappcompose.R
import com.jean.cinemappcompose.presentation.common.component.AppNameText
import com.jean.cinemappcompose.presentation.common.component.DefaultButton
import com.jean.cinemappcompose.presentation.ui.theme.font_baloo

@Composable
fun WelcomeScreen(
    signInClicked: () -> Unit,
    signUpClicked: () -> Unit
) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
        .background(color = MaterialTheme.colors.background)) {
        Spacer(modifier = Modifier.size(40.dp))
        WelcomeContent()
        WelcomeBottom(
            signInClicked = { signInClicked() },
            signUpClicked = { signUpClicked() }
        )
    }
}

@Composable
fun WelcomeContent() {
    AppNameText()
    Spacer(modifier = Modifier.padding(top = 30.dp))
    Text(
        text = stringResource(id = R.string.welcome_title),
        fontSize = 24.sp,
        fontFamily = font_baloo,
        color = MaterialTheme.colors.onBackground
    )
    Spacer(modifier = Modifier.padding(top = 20.dp))
    Text(
        text = stringResource(id = R.string.welcome_description),
        fontSize = 20.sp,
        fontFamily = font_baloo,
        color = MaterialTheme.colors.onBackground
    )
}

@Composable
fun WelcomeBottom(
    signInClicked: () -> Unit,
    signUpClicked: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.Bottom
    ) {
        DefaultButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(45.dp),
            label = stringResource(id = R.string.sign_in_button_text),
            onButtonClicked = { signInClicked() }
        )
        Spacer(modifier = Modifier.padding(top = 15.dp))
        OutlinedButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(45.dp),
            border = BorderStroke(1.dp, MaterialTheme.colors.primary),
            onClick = { signUpClicked() }
        ) {
            Text(
                text = stringResource(id = R.string.sign_up_button_text).uppercase(),
                fontSize = 16.sp
            )
        }
    }
}