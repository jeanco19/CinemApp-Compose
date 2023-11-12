package com.jean.cinemappcompose.movie.domain.usecase.genre

import com.google.common.truth.Truth
import com.jean.cinemappcompose.core.util.genres
import com.jean.cinemappcompose.movie.data.mapper.toDomain
import com.jean.cinemappcompose.movie.data.repository.FakeGenresRepository
import com.jean.cinemappcompose.movie.domain.models.Genre
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetMovieGenresUseCaseImplTest {

    private lateinit var fakeGenresRepository: FakeGenresRepository
    private lateinit var sut: GetMovieGenresUseCase

    @Before
    fun setup() {
        fakeGenresRepository = FakeGenresRepository()
        sut = GetMovieGenresUseCaseImpl(fakeGenresRepository)
    }

    @Test
    fun `validate when getting genres is successfully, then returns all genre data`() = runTest {
        val expectedData = genres.map { it.toDomain() }
        var currentData = listOf<Genre>()

        sut.invoke().collect { result ->
            result.map { genres ->
                currentData = genres
            }
        }

        Truth.assertThat(currentData).isEqualTo(expectedData)
        Truth.assertThat(currentData[0].id).isNotNull()
        Truth.assertThat(currentData[0].name).isNotEmpty()
    }

}