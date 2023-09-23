package com.jean.cinemappcompose.auth.domain.usecase.validator

import com.google.common.truth.Truth
import com.jean.cinemappcompose.auth.domain.model.EmailResult
import org.junit.Before
import org.junit.Test

class EmailValidatorUseCaseImplTest {

    private lateinit var fakeFakeEmailPatternValidator: FakeEmailPatternValidator

    private lateinit var sut: EmailValidatorUseCase

    @Before
    fun setup() {
        fakeFakeEmailPatternValidator = FakeEmailPatternValidator()
        sut = EmailValidatorUseCaseImpl(fakeFakeEmailPatternValidator)
    }

    @Test
    fun `check when user enter a valid email`() {
        val input = "prueba@gmail.com"
        val expectedResult = EmailResult.VALID.name

        val emailResult = sut.invoke(input)

        Truth.assertThat(emailResult).isEqualTo(expectedResult)
    }

    @Test
    fun `check when user enter a invalid email`() {
        val input = "pruebagmail.cm"
        val expectedResult = EmailResult.INVALID_PATTERN.name

        val emailResult = sut.invoke(input)

        Truth.assertThat(emailResult).isEqualTo(expectedResult)
    }

}