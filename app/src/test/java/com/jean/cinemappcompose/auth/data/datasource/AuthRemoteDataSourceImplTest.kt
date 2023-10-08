package com.jean.cinemappcompose.auth.data.datasource

import com.google.common.truth.Truth
import com.google.firebase.auth.FirebaseAuthException
import com.jean.cinemappcompose.auth.domain.model.RestartPasswordErrorResult
import com.jean.cinemappcompose.auth.domain.model.SignInErrorResult
import com.jean.cinemappcompose.auth.domain.model.SignUpErrorResult
import com.jean.cinemappcompose.auth.util.toRestartPasswordErrorTypes
import com.jean.cinemappcompose.auth.util.toSignInErrorTypes
import com.jean.cinemappcompose.auth.util.toSignUpErrorTypes
import com.jean.cinemappcompose.core.util.Constants.EMPTY_STRING
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AuthRemoteDataSourceImplTest {

    companion object {
        const val UNKNOWN_EXCEPTION = "ERROR"
    }

    @get:Rule
    val mockkRule = MockKRule(this)

    @MockK(relaxed = true)
    lateinit var firebaseAuthException: FirebaseAuthException

    @Test
    fun `validate when sign in function return known exception is parsing to signInErrorResult`() {
        val expectedErrorType = SignInErrorResult.PASSWORD_WRONG.name

        val errorType = firebaseAuthException.toSignInErrorTypes("ERROR_WRONG_PASSWORD")

        Truth.assertThat(errorType).isEqualTo(expectedErrorType)
    }

    @Test
    fun `validate when sign in function return unknown exception is parsing to default signInErrorResult`() {
        val expectedErrorType = SignInErrorResult.SIGN_IN_ERROR.name

        val errorType = firebaseAuthException.toSignInErrorTypes(UNKNOWN_EXCEPTION)

        Truth.assertThat(errorType).isEqualTo(expectedErrorType)
    }

    @Test
    fun `validate when sign in function return empty exception is parsing to default signInErrorResult`() {
        val expectedErrorType = SignInErrorResult.SIGN_IN_ERROR.name

        val errorType = firebaseAuthException.toSignInErrorTypes(EMPTY_STRING)

        Truth.assertThat(errorType).isEqualTo(expectedErrorType)
    }

    @Test
    fun `validate when sign up function return known exception is parsing to signUpErrorResult`() {
        val expectedErrorType = SignUpErrorResult.EMAIL_ALREADY_IN_USE.name

        val errorType = firebaseAuthException.toSignUpErrorTypes("ERROR_EMAIL_ALREADY_IN_USE")

        Truth.assertThat(errorType).isEqualTo(expectedErrorType)
    }

    @Test
    fun `validate when sign up function return unknown exception is parsing to default signUpErrorResult`() {
        val expectedErrorType = SignUpErrorResult.SIGN_UP_ERROR.name

        val errorType = firebaseAuthException.toSignUpErrorTypes(UNKNOWN_EXCEPTION)

        Truth.assertThat(errorType).isEqualTo(expectedErrorType)
    }

    @Test
    fun `validate when sign up function return empty exception is parsing to default signUpErrorResult`() {
        val expectedErrorType = SignUpErrorResult.SIGN_UP_ERROR.name

        val errorType = firebaseAuthException.toSignUpErrorTypes(EMPTY_STRING)

        Truth.assertThat(errorType).isEqualTo(expectedErrorType)
    }

    @Test
    fun `validate when restart password function return known exception is parsing to restartPasswordErrorResult`() {
        val expectedErrorType = RestartPasswordErrorResult.USER_NOT_FOUND.name

        val errorType = firebaseAuthException.toRestartPasswordErrorTypes("ERROR_USER_NOT_FOUND")

        Truth.assertThat(errorType).isEqualTo(expectedErrorType)
    }

    @Test
    fun `validate when restart password function return unknown exception is parsing to default restartPasswordErrorResult`() {
        val expectedErrorType = RestartPasswordErrorResult.RESTART_PASSWORD_ERROR.name

        val errorType = firebaseAuthException.toRestartPasswordErrorTypes(UNKNOWN_EXCEPTION)

        Truth.assertThat(errorType).isEqualTo(expectedErrorType)
    }

    @Test
    fun `validate when restart password function return empty exception is parsing to default restartPasswordErrorResult`() {
        val expectedErrorType = RestartPasswordErrorResult.RESTART_PASSWORD_ERROR.name

        val errorType = firebaseAuthException.toRestartPasswordErrorTypes(EMPTY_STRING)

        Truth.assertThat(errorType).isEqualTo(expectedErrorType)
    }

}