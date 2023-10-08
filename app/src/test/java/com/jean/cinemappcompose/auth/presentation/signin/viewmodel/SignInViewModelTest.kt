package com.jean.cinemappcompose.auth.presentation.signin.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import com.jean.cinemappcompose.auth.domain.usecase.signin.SignInUseCase
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
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SignInViewModelTest {

    companion object {
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
    lateinit var signInUseCase: SignInUseCase

    private lateinit var fakeFakeEmailPatternValidator: FakeEmailPatternValidator
    private lateinit var fakeNetworkConnectivityObserver: FakeNetworkConnectivityObserver
    private lateinit var emailValidatorUseCase: EmailValidatorUseCase
    private lateinit var passwordValidatorUseCase: PasswordValidatorUseCase
    private lateinit var connectivityManagerUseCase: ConnectivityManagerUseCase
    private lateinit var sut: SignInViewModel

    @Before
    fun setup() {
        fakeFakeEmailPatternValidator = FakeEmailPatternValidator()
        emailValidatorUseCase = EmailValidatorUseCaseImpl(fakeFakeEmailPatternValidator)
        passwordValidatorUseCase = PasswordValidatorUseCaseImpl()
        fakeNetworkConnectivityObserver = FakeNetworkConnectivityObserver()
        connectivityManagerUseCase = ConnectivityManagerUseCaseImpl(fakeNetworkConnectivityObserver)

        sut = SignInViewModel(
            signInUseCase,
            emailValidatorUseCase,
            passwordValidatorUseCase,
            connectivityManagerUseCase
        )
    }

    @Test
    fun `check initial values in state class`() {
        val state = sut.uiState
        val expectedState = SignInUiState(
            isLoading = false,
            isSignedIn = false,
            isButtonEnable = false,
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
    fun `validate when user enter a email, the state is updated`() {
        val emailInput = VALID_EMAIL
        val emailDefault = EMPTY_STRING

        val initialState = sut.uiState.email
        Truth.assertThat(initialState).isEqualTo(emailDefault)

        sut.onEvent(SignInEvent.EmailChange(emailInput))
        val updateState = sut.uiState.email

        Truth.assertThat(updateState).isEqualTo(emailInput)
    }

    @Test
    fun `validate when user enter a password, the state is updated`() {
        val passwordInput = VALID_PASSWORD
        val passwordDefault = EMPTY_STRING

        val initialState = sut.uiState.password
        Truth.assertThat(initialState).isEqualTo(passwordDefault)

        sut.onEvent(SignInEvent.PasswordChange(passwordInput))
        val updateState = sut.uiState.password

        Truth.assertThat(updateState).isEqualTo(passwordInput)
    }

    @Test
    fun `validate when user do sign in with a valid email, the state not show email error`() {
        val emailInput = VALID_EMAIL

        sut.onEvent(SignInEvent.EmailChange(emailInput))
        sut.onEvent(SignInEvent.SignIn)

        val updateState = sut.uiState.emailError
        Truth.assertThat(updateState).isNull()
    }

    @Test
    fun `validate when user do sign in with a valid password, the state not show password error`() {
        val passwordInput = VALID_PASSWORD

        sut.onEvent(SignInEvent.PasswordChange(passwordInput))
        sut.onEvent(SignInEvent.SignIn)

        val updateState = sut.uiState.passwordError
        Truth.assertThat(updateState).isNull()
    }

    @Test
    fun `validate when user do sign in with an invalid email, the state show email error`() {
        val emailInput = "pruebamailcom"

        sut.onEvent(SignInEvent.EmailChange(emailInput))
        sut.onEvent(SignInEvent.SignIn)

        val updateState = sut.uiState.emailError
        Truth.assertThat(updateState).isNotNull()
    }

    @Test
    fun `validate when user do sign in with an invalid password, the state show password error`() {
        val passwordInput = "123"

        sut.onEvent(SignInEvent.PasswordChange(passwordInput))
        sut.onEvent(SignInEvent.SignIn)

        val updateState = sut.uiState.passwordError
        Truth.assertThat(updateState).isNotNull()
    }

    @Test
    fun `validate when user enter a valid email and password, the button state is enabled`() {
        val emailInput = VALID_EMAIL
        val passwordInput = VALID_PASSWORD

        sut.onEvent(SignInEvent.EmailChange(emailInput))
        sut.onEvent(SignInEvent.PasswordChange(passwordInput))

        val updateState = sut.uiState.isButtonEnable
        Truth.assertThat(updateState).isTrue()
    }

}