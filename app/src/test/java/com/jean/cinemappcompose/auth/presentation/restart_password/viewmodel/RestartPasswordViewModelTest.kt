package com.jean.cinemappcompose.auth.presentation.restart_password.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth
import com.jean.cinemappcompose.auth.domain.usecase.restart_password.RestartPasswordUseCase
import com.jean.cinemappcompose.auth.domain.usecase.validator.EmailValidatorUseCase
import com.jean.cinemappcompose.auth.domain.usecase.validator.EmailValidatorUseCaseImpl
import com.jean.cinemappcompose.auth.domain.usecase.validator.FakeEmailPatternValidator
import com.jean.cinemappcompose.auth.presentation.signin.viewmodel.SignInViewModelTest
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

class RestartPasswordViewModelTest {

    companion object {
        const val VALID_EMAIL = "prueba@gmail.com"
    }

    @get:Rule
    val mockkRule = MockKRule(this)

    @get: Rule
    val mainCoroutineRule = MainCoroutineRule()

    @get: Rule
    val instantTaskExecutor = InstantTaskExecutorRule()

    @MockK(relaxed = true)
    lateinit var restartPasswordUseCase: RestartPasswordUseCase

    private lateinit var fakeFakeEmailPatternValidator: FakeEmailPatternValidator
    private lateinit var fakeNetworkConnectivityObserver: FakeNetworkConnectivityObserver
    private lateinit var emailValidatorUseCase: EmailValidatorUseCase
    private lateinit var connectivityManagerUseCase: ConnectivityManagerUseCase
    private lateinit var sut: RestartPasswordViewModel

    @Before
    fun setup() {
        fakeFakeEmailPatternValidator = FakeEmailPatternValidator()
        emailValidatorUseCase = EmailValidatorUseCaseImpl(fakeFakeEmailPatternValidator)
        fakeNetworkConnectivityObserver = FakeNetworkConnectivityObserver()
        connectivityManagerUseCase = ConnectivityManagerUseCaseImpl(fakeNetworkConnectivityObserver)

        sut = RestartPasswordViewModel(
            restartPasswordUseCase,
            emailValidatorUseCase,
            connectivityManagerUseCase
        )
    }

    @Test
    fun `check initial values in state class`() {
        val state = sut.uiState
        val expectedState = RestartPasswordUiState(
            isLoading = false,
            isSendEmail = false,
            isButtonEnable = false,
            email = EMPTY_STRING,
            emailError = null,
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

        sut.onEvent(RestartPasswordEvent.EmailChange(emailInput))
        val updateState = sut.uiState.email

        Truth.assertThat(updateState).isEqualTo(emailInput)
    }

    @Test
    fun `validate when user restart password with a valid email, the state not show email error`() {
        val emailInput = VALID_EMAIL

        sut.onEvent(RestartPasswordEvent.EmailChange(emailInput))
        sut.onEvent(RestartPasswordEvent.RestartPassword)

        val updateState = sut.uiState.emailError
        Truth.assertThat(updateState).isNull()
    }

    @Test
    fun `validate when user do sign in with an invalid email, the state show email error`() {
        val emailInput = "pruebamailcom"

        sut.onEvent(RestartPasswordEvent.EmailChange(emailInput))
        sut.onEvent(RestartPasswordEvent.RestartPassword)

        val updateState = sut.uiState.emailError
        Truth.assertThat(updateState).isNotNull()
    }

    @Test
    fun `validate when user enter a valid email, the button state is enabled`() {
        val emailInput = SignInViewModelTest.VALID_EMAIL

        sut.onEvent(RestartPasswordEvent.EmailChange(emailInput))

        val updateState = sut.uiState.isButtonEnable
        Truth.assertThat(updateState).isTrue()
    }

}