package com.jean.cinemappcompose.auth.domain.usecase.validator

import com.google.common.truth.Truth
import com.jean.cinemappcompose.auth.domain.model.PasswordResult
import org.junit.Before
import org.junit.Test

class PasswordValidatorUseCaseImplTest {

    private lateinit var sut: PasswordValidatorUseCase

    @Before
    fun setup() {
        sut = PasswordValidatorUseCaseImpl()
    }

    @Test
    fun `check when user enter a valid password length`() {
        val input = "12345678"
        val expectedResult = PasswordResult.VALID.name

        val passwordResult = sut.invoke(input)

        Truth.assertThat(passwordResult).isEqualTo(expectedResult)
    }

    @Test
    fun `check when user enter a invalid password length`() {
        val input = "123"
        val expectedResult = PasswordResult.INVALID_LENGTH.name

        val passwordResult = sut.invoke(input)

        Truth.assertThat(passwordResult).isEqualTo(expectedResult)
    }

}