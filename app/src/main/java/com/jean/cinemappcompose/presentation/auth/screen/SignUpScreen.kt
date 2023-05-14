package com.jean.cinemappcompose.presentation.auth.screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jean.cinemappcompose.R
import com.jean.cinemappcompose.presentation.auth.viewmodel.SignUpViewModel
import com.jean.cinemappcompose.presentation.util.*

private const val EMPTY_STRING = ""

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SignUpScreen(
    signUpClicked: () -> Unit,
    signInClicked: () -> Unit,
    viewModel: SignUpViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    val controller = LocalSoftwareKeyboardController.current

    var name by remember { mutableStateOf(EMPTY_STRING) }
    var lastName by remember { mutableStateOf(EMPTY_STRING) }
    var email by remember { mutableStateOf(EMPTY_STRING) }
    var password by remember { mutableStateOf(EMPTY_STRING) }
    var isSignUpEnabled by remember { mutableStateOf(false) }
    isSignUpEnabled = viewModel.handleButtonEnable(name, lastName, email, password)

    LaunchedEffect(key1 = viewModel.uiState.isSignedUp) {
        if (viewModel.uiState.isSignedUp) {
            Toast.makeText(context, R.string.sign_up_successfully, Toast.LENGTH_SHORT).show()
            signUpClicked()
            viewModel.resetFieldSignedUp()
        }
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
        .background(MaterialTheme.colors.background)) {
        if (viewModel.uiState.isLoading) CustomProgressDialog()
        Spacer(modifier = Modifier.size(30.dp))
        AppNameTitle()
        Spacer(modifier = Modifier.size(30.dp))
        FormTextField(
            value = name,
            label = stringResource(id = R.string.name_field_placeholder),
            onTextChanged = { name = it }
        )
        Spacer(modifier = Modifier.size(10.dp))
        FormTextField(
            value = lastName,
            label = stringResource(id = R.string.last_name_field_placeholder),
            onTextChanged = { lastName = it }
        )
        Spacer(modifier = Modifier.size(10.dp))
        EmailTextField(
            email = email,
            hasError = viewModel.uiState.hasEmailError,
            errorMessage = viewModel.uiState.errorFieldMessage,
            onTextChanged = { email = it }
        )
        Spacer(modifier = Modifier.size(10.dp))
        PasswordTextField(
            password = password,
            hasError = viewModel.uiState.hasPasswordError,
            errorMessage = viewModel.uiState.errorFieldMessage,
            onTextChanged = { password = it }
        )
        Spacer(modifier = Modifier.size(50.dp))
        SingleButton(
            label = stringResource(id = R.string.sign_up_button_text),
            isButtonEnabled = isSignUpEnabled,
            onButtonClicked = {
                controller?.hide()
                viewModel.resetFieldErrorMessages()
                viewModel.doSignUp(
                    name = name,
                    lastName = lastName,
                    email = email,
                    password = password
                )
            }
        )
        SignInOption {
            signInClicked()
        }
    }
}

@Composable
fun FormTextField(
    value: String,
    label: String,
    onTextChanged: (String) -> Unit
) {
    Column {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = label) },
            value = value,
            onValueChange = { onTextChanged(it) },
            maxLines = 1,
            singleLine = true,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = MaterialTheme.colors.primary,
                focusedLabelColor = MaterialTheme.colors.primary
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )
    }
}

@Composable
fun SignInOption(onTextPressed: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxSize(),
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
            color = MaterialTheme.colors.primary
        )
    }
}