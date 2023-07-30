package com.jean.cinemappcompose.auth.presentation.welcome.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jean.cinemappcompose.R
import com.jean.cinemappcompose.presentation.common.component.DefaultButton

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