package com.jean.cinemappcompose.auth.presentation.signin.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jean.cinemappcompose.R

@Composable
fun SignInBottom(onTextPressed: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Bottom
    ) {
        Text(
            text = stringResource(id = R.string.still_do_not_have_account_text),
            fontSize = 15.sp,
            color = Color.Gray
        )
        Text(
            modifier = Modifier
                .padding(start = 1.dp)
                .clickable { onTextPressed() },
            text = stringResource(id = R.string.sign_up_here_text),
            fontSize = 15.sp,
            color = MaterialTheme.colors.primary
        )
    }
}