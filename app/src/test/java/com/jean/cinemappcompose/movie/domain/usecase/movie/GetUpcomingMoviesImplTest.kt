package com.jean.cinemappcompose.movie.domain.usecase.movie

import com.google.common.truth.Truth
import com.jean.cinemappcompose.core.util.upcomingMovies
import com.jean.cinemappcompose.movie.data.mapper.toDomain
import com.jean.cinemappcompose.movie.data.repository.FakeMoviesRepository
import com.jean.cinemappcompose.movie.domain.models.Movie
import com.jean.cinemappcompose.movie.domain.models.MovieType
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class GetUpcomingMoviesImplTest {

    private lateinit var fakeMoviesRepository: FakeMoviesRepository

    private lateinit var sut: GetUpcomingMovies

    @Before
    fun setup() {
        fakeMoviesRepository = FakeMoviesRepository()
        sut = GetUpcomingMoviesImpl(fakeMoviesRepository)
    }

    @Test
    fun `validate when getting upcoming movies, then returns all movie data`() = runTest {
        val expectedData = upcomingMovies.map { it.toDomain() }
        var currentData = listOf<Movie>()

        sut.invoke().collect { result ->
            result.map { movies ->
                currentData = movies
            }
        }

        Truth.assertThat(currentData).isEqualTo(expectedData)
        Truth.assertThat(currentData[0].id).isNotNull()
        Truth.assertThat(currentData[0].title).isNotEmpty()
        Truth.assertThat(currentData[0].type).isEqualTo(MovieType.UPCOMING)
        Truth.assertThat(currentData[0].overview).isNotEmpty()
        Truth.assertThat(currentData[0].posterPath).isNotEmpty()
        Truth.assertThat(currentData[0].releaseDate).isNotEmpty()
        Truth.assertThat(currentData[0].voteAverage).isNotNull()
        Truth.assertThat(currentData[0].genreIds).isNotEmpty()
    }

}