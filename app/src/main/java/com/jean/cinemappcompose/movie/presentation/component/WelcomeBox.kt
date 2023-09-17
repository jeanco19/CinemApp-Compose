package com.jean.cinemappcompose.movie.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jean.cinemappcompose.R
import com.jean.cinemappcompose.core.presentation.component.AppNameText

@Composable
fun WelcomeBox(username: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            AppNameText(
                size = 28.sp,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
        Text(
            modifier = Modifier.padding(top = 30.dp, start = 10.dp, bottom = 5.dp),
            text = stringResource(id = R.string.movie_welcome_title, username),
            fontSize = 22.sp,
            color = MaterialTheme.colorScheme.onPrimary,
        )
        Text(
            modifier = Modifier.padding(start = 10.dp, bottom = 20.dp),
            text = stringResource(id = R.string.movie_welcome_description),
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.onPrimary,
            fontWeight = FontWeight.ExtraLight
        )
    }
}