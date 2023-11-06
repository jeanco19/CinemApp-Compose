package com.jean.cinemappcompose.movie.data.repository

import com.google.common.truth.Truth
import com.jean.cinemappcompose.core.util.genres
import com.jean.cinemappcompose.movie.data.datasource.genre.FakeGenreLocalDataSourceImpl
import com.jean.cinemappcompose.movie.data.datasource.genre.GenresLocalDataSource
import com.jean.cinemappcompose.movie.data.datasource.genre.GenresRemoteDataSource
import com.jean.cinemappcompose.movie.data.mapper.toDomain
import com.jean.cinemappcompose.movie.domain.models.Genre
import com.jean.cinemappcompose.movie.domain.repository.GenresRepository
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GenresRepositoryImplTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    @MockK(relaxed = true)
    lateinit var genreRemoteSource: GenresRemoteDataSource
    private lateinit var genreLocalSource: GenresLocalDataSource

    private lateinit var sut: GenresRepository

    @Before
    fun setup() {
        genreLocalSource = FakeGenreLocalDataSourceImpl()
        sut = GenresRepositoryImpl(genreRemoteSource, genreLocalSource)
    }

    @Test
    fun `validate when genres local source is not empty, then retrieved a local data`() = runTest {
        val initialData = genres
        var currentData = listOf<Genre>()
        genreLocalSource.insertGenres(initialData)

        sut.getMovieGenres().collect { result ->
            result.map { genres ->
                currentData = genres
            }
        }

        Truth.assertThat(currentData).isNotEmpty()
        Truth.assertThat(currentData).isEqualTo(initialData.map { it.toDomain() })
        Truth.assertThat(currentData[0].id).isNotNull()
        Truth.assertThat(currentData[0].name).isNotEmpty()
    }

    @Test
    fun `validate when genres local source is empty, then retrieved a remote data`() = runTest {
        var initialData = emptyList<Genre>()
        var currentData = emptyList<Genre>()
        val expectedData = genres

        sut.getMovieGenres().collect { result ->
            result.map {
                initialData = it
            }
        }

        Truth.assertThat(initialData).isEmpty()
        genreLocalSource.insertGenres(genres)

        sut.getMovieGenres().collect { result ->
            result.map {
                currentData = it
            }
        }

        Truth.assertThat(currentData).isNotEmpty()
        Truth.assertThat(currentData).isEqualTo(expectedData.map { it.toDomain() })
    }

}