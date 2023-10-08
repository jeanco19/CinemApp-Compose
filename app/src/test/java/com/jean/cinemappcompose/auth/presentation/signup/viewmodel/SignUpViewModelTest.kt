package com.jean.cinemappcompose.auth.presentation.signup.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import com.jean.cinemappcompose.auth.domain.usecase.signup.SignUpUseCase
import com.jean.cinemappcompose.auth.domain.usecase.validator.EmailValidatorUseCase
import com.jean.cinemappcompose.auth.domain.usecase.validator.EmailValidatorUseCaseImpl
import com.jean.cinemappcompose.auth.domain.usecase.validator.FakeEmailPatternValidator
import com.jean.cinemappcompose.auth.domain.usecase.validator.PasswordValidatorUseCase
import com.jean.cinemappcompose.auth.domain.usecase.validator.PasswordValidatorUseCaseImpl
import com.jean.cinemappcompose.core.domain.usecase.ConnectivityManagerUseCase
import com.jean.cinemappcompose.core.domain.usecase.ConnectivityManagerUseCaseImpl
import com.jean.cinemappcompose.core.util.Constants.EMPTY_STRING
import com.jean.cinemappcompose.core.util.MainCoroutineRule
import com.jean.cinemappcompose.core.util.connectivity.FakeNetworkConnectivityObserver
import com.jean.cinemappcompose.profile.domain.usecase.CreateUserUseCase
import com.jean.cinemappcompose.profile.domain.usecase.GetCurrentUserIdUseCase
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SignUpViewModelTest {

    companion object {
        const val VALID_NAME = "Jose"
        const val VALID_LAST_NAME = "Perez"
        const val VALID_EMAIL = "prueba@gmail.com"
        const val VALID_PASSWORD = "12345678"
    }

    @get:Rule
    val mockkRule = MockKRule(this)

    @get: Rule
    val mainCoroutineRule = MainCoroutineRule()

    @get: Rule
    val instantTaskExecutor = InstantTaskExecutorRule()

    @MockK(relaxed = true)
    lateinit var signUpUseCase: SignUpUseCase

    @MockK(relaxed = true)
    lateinit var createUserUseCase: CreateUserUseCase

    @MockK(relaxed = true)
    lateinit var getCurrentUserIdUseCase: GetCurrentUserIdUseCase

    private lateinit var fakeFakeEmailPatternValidator: FakeEmailPatternValidator
    private lateinit var fakeNetworkConnectivityObserver: FakeNetworkConnectivityObserver
    private lateinit var emailValidatorUseCase: EmailValidatorUseCase
    private lateinit var passwordValidatorUseCase: PasswordValidatorUseCase
    private lateinit var connectivityManagerUseCase: ConnectivityManagerUseCase
    private lateinit var sut: SignUpViewModel

    @Before
    fun setup() {
        fakeFakeEmailPatternValidator = FakeEmailPatternValidator()
        emailValidatorUseCase = EmailValidatorUseCaseImpl(fakeFakeEmailPatternValidator)
        passwordValidatorUseCase = PasswordValidatorUseCaseImpl()
        fakeNetworkConnectivityObserver = FakeNetworkConnectivityObserver()
        connectivityManagerUseCase = ConnectivityManagerUseCaseImpl(fakeNetworkConnectivityObserver)

        sut = SignUpViewModel(
            signUpUseCase,
            emailValidatorUseCase,
            passwordValidatorUseCase,
            getCurrentUserIdUseCase,
            createUserUseCase,
            connectivityManagerUseCase
        )
    }

    @Test
    fun `check initial values in state class`() {
        val state = sut.uiState
        val expectedState = SignUpUiState(
            isLoading = false,
            isSignedUp = false,
            isButtonEnable = false,
            name = EMPTY_STRING,
            lastName = EMPTY_STRING,
            email = EMPTY_STRING,
            password = EMPTY_STRING,
            emailError = null,
            passwordError = null,
            generalError = null,
            hasConnectivity = true,
            connectivityMessage = 2131558403
        )

        Truth.assertThat(state).isEqualTo(expectedState)
    }

    @Test
    fun `validate when user enter a name, the state is updated`() {
        val nameInput = VALID_NAME
        val nameDefault = EMPTY_STRING

        val initialState = sut.uiState.name
        Truth.assertThat(initialState).isEqualTo(nameDefault)

        sut.onEvent(SignUpEvent.NameChange(nameInput))
        val updateState = sut.uiState.name

        Truth.assertThat(updateState).isEqualTo(nameInput)
    }

    @Test
    fun `validate when user enter a lastName, the state is updated`() {
        val lastNameInput = VALID_LAST_NAME
        val lastNameDefault = EMPTY_STRING

        val initialState = sut.uiState.lastName
        Truth.assertThat(initialState).isEqualTo(lastNameDefault)

        sut.onEvent(SignUpEvent.LastName(lastNameInput))
        val updateState = sut.uiState.lastName

        Truth.assertThat(updateState).isEqualTo(lastNameInput)
    }

    @Test
    fun `validate when user enter a email, the state is updated`() {
        val emailInput = VALID_EMAIL
        val emailDefault = EMPTY_STRING

        val initialState = sut.uiState.email
        Truth.assertThat(initialState).isEqualTo(emailDefault)

        sut.onEvent(SignUpEvent.EmailChange(emailInput))
        val updateState = sut.uiState.email

        Truth.assertThat(updateState).isEqualTo(emailInput)
    }

    @Test
    fun `validate when user enter a password, the state is updated`() {
        val passwordInput = VALID_PASSWORD
        val passwordDefault = EMPTY_STRING

        val initialState = sut.uiState.password
        Truth.assertThat(initialState).isEqualTo(passwordDefault)

        sut.onEvent(SignUpEvent.PasswordChange(passwordInput))
        val updateState = sut.uiState.password

        Truth.assertThat(updateState).isEqualTo(passwordInput)
    }

    @Test
    fun `validate when user do sign up with a valid email, the state not show email error`() {
        val emailInput = VALID_EMAIL

        sut.onEvent(SignUpEvent.EmailChange(emailInput))
        sut.onEvent(SignUpEvent.SignUp)

        val updateState = sut.uiState.emailError
        Truth.assertThat(updateState).isNull()
    }

    @Test
    fun `validate when user do sign up with a valid password, the state not show password error`() {
        val emailInput = VALID_PASSWORD

        sut.onEvent(SignUpEvent.PasswordChange(emailInput))
        sut.onEvent(SignUpEvent.SignUp)

        val updateState = sut.uiState.passwordError
        Truth.assertThat(updateState).isNull()
    }

    @Test
    fun `validate when user do sign up with an invalid email, the state show email error`() {
        val emailInput = "pruebamailcom"

        sut.onEvent(SignUpEvent.EmailChange(emailInput))
        sut.onEvent(SignUpEvent.SignUp)

        val updateState = sut.uiState.emailError
        Truth.assertThat(updateState).isNotNull()
    }

    @Test
    fun `validate when user do sign up with an invalid password, the state show password error`() {
        val emailInput = "123"

        sut.onEvent(SignUpEvent.PasswordChange(emailInput))
        sut.onEvent(SignUpEvent.SignUp)

        val updateState = sut.uiState.passwordError
        Truth.assertThat(updateState).isNotNull()
    }

    @Test
    fun `validate when user enter a valid name, lastname, email and password, the button state is enabled`() {
        val nameInput = VALID_NAME
        val lastNameInput = VALID_LAST_NAME
        val emailInput = VALID_EMAIL
        val passwordInput = VALID_PASSWORD

        sut.onEvent(SignUpEvent.NameChange(nameInput))
        sut.onEvent(SignUpEvent.LastName(lastNameInput))
        sut.onEvent(SignUpEvent.EmailChange(emailInput))
        sut.onEvent(SignUpEvent.PasswordChange(passwordInput))

        val updateState = sut.uiState.isButtonEnable
        Truth.assertThat(updateState).isTrue()
    }

}