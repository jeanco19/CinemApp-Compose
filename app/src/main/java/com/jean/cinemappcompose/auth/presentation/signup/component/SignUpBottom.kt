package com.jean.cinemappcompose.auth.presentation.signup.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jean.cinemappcompose.R

@Composable
fun SignUpBottom(onTextPressed: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Bottom
    ) {
        Text(
            text = stringResource(id = R.string.already_have_an_account),
            fontSize = 16.sp,
            color = Color.Gray
        )
        Text(
            modifier = Modifier
                .padding(start = 1.dp)
                .clickable { onTextPressed() },
            text = stringResource(id = R.string.sign_in_here_text),
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.primary
        )
    }
}