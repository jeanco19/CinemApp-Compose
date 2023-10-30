package com.jean.cinemappcompose.movie.data.repository

import com.google.common.truth.Truth
import com.jean.cinemappcompose.core.util.inTheaterMovies
import com.jean.cinemappcompose.core.util.upcomingMovies
import com.jean.cinemappcompose.movie.data.datasource.movie.FakeMoviesLocalDataSourceImpl
import com.jean.cinemappcompose.movie.data.datasource.movie.MoviesLocalDataSource
import com.jean.cinemappcompose.movie.data.datasource.movie.MoviesRemoteDataSource
import com.jean.cinemappcompose.movie.data.mapper.toDomain
import com.jean.cinemappcompose.movie.domain.models.Movie
import com.jean.cinemappcompose.movie.domain.models.MovieType
import com.jean.cinemappcompose.movie.domain.repository.MoviesRepository
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MoviesRepositoryImplTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    private lateinit var moviesLocalSource: MoviesLocalDataSource
    @MockK(relaxed = true)
    lateinit var moviesRemoteSource: MoviesRemoteDataSource

    private lateinit var sut: MoviesRepository

    @Before
    fun setup() {
        moviesLocalSource = FakeMoviesLocalDataSourceImpl()
        sut = MoviesRepositoryImpl(moviesRemoteSource, moviesLocalSource)
    }

    @Test
    fun `validate when in theater movies local source is not empty, then retrieved a local data`() = runTest {
        val initialData = inTheaterMovies
        var currentData = listOf<Movie>()
        moviesLocalSource.insertInTheaterMovies(initialData)

        sut.getInTheaterMovies().collect { result ->
            result.map {  movies ->
                currentData = movies
            }
        }

        Truth.assertThat(currentData).isNotEmpty()
        Truth.assertThat(currentData).isEqualTo(initialData.map { it.toDomain() })
        Truth.assertThat(currentData[0].id).isNotNull()
        Truth.assertThat(currentData[0].title).isNotEmpty()
        Truth.assertThat(currentData[0].overview).isNotEmpty()
        Truth.assertThat(currentData[0].posterPath).isNotEmpty()
    }

    @Test
    fun `validate when in theater movies local source is empty, then retrieved a remote data`() = runTest {
        var initialData = emptyList<Movie>()

        sut.getInTheaterMovies().collect { result ->
            result.map { movies ->
                initialData = movies
            }
        }

        Truth.assertThat(initialData).isEmpty()

        var currentData = emptyList<Movie>()
        val expectedData = inTheaterMovies

        moviesLocalSource.insertInTheaterMovies(inTheaterMovies)
        sut.getInTheaterMovies().collect { result ->
            result.map { movies ->
                currentData = movies
            }
        }

        Truth.assertThat(currentData).isNotEmpty()
        Truth.assertThat(currentData).isEqualTo(expectedData.map { it.toDomain() })
    }

    @Test
    fun `validate when search in theater movies in local source, then retrieved a in theater movie list`() = runTest {
        val expectedType = MovieType.IN_THEATER
        val initialData = inTheaterMovies
        var currentData = listOf<Movie>()
        moviesLocalSource.insertInTheaterMovies(initialData)

        sut.getInTheaterMovies().collect { result ->
            result.map {  movies ->
                currentData = movies
            }
        }

        Truth.assertThat(currentData).isNotEmpty()
        Truth.assertThat(currentData[0].type).isEqualTo(expectedType)
    }







    @Test
    fun `validate when upcoming movies local source is not empty, then retrieved a local data`() = runTest {
        val initialData = upcomingMovies
        var currentData = listOf<Movie>()
        moviesLocalSource.insertUpcomingMovies(initialData)

        sut.getUpcomingMovies().collect { result ->
            result.map {  movies ->
                currentData = movies
            }
        }

        Truth.assertThat(currentData).isNotEmpty()
        Truth.assertThat(currentData).isEqualTo(initialData.map { it.toDomain() })
        Truth.assertThat(currentData[0].id).isNotNull()
        Truth.assertThat(currentData[0].title).isNotEmpty()
        Truth.assertThat(currentData[0].overview).isNotEmpty()
        Truth.assertThat(currentData[0].posterPath).isNotEmpty()
    }

    @Test
    fun `validate when upcoming movies local source is empty, then retrieved a remote data`() = runTest {
        var initialData = emptyList<Movie>()

        sut.getUpcomingMovies().collect { result ->
            result.map { movies ->
                initialData = movies
            }
        }

        Truth.assertThat(initialData).isEmpty()

        var currentData = emptyList<Movie>()
        val expectedData = upcomingMovies

        moviesLocalSource.insertUpcomingMovies(upcomingMovies)
        sut.getUpcomingMovies().collect { result ->
            result.map { movies ->
                currentData = movies
            }
        }

        Truth.assertThat(currentData).isNotEmpty()
        Truth.assertThat(currentData).isEqualTo(expectedData.map { it.toDomain() })
    }

    @Test
    fun `validate when search upcoming movies in local source, then retrieved an upcoming movie list`() = runTest {
        val expectedType = MovieType.UPCOMING
        val initialData = upcomingMovies
        var currentData = listOf<Movie>()
        moviesLocalSource.insertUpcomingMovies(initialData)

        sut.getUpcomingMovies().collect { result ->
            result.map {  movies ->
                currentData = movies
            }
        }

        Truth.assertThat(currentData).isNotEmpty()
        Truth.assertThat(currentData[0].type).isEqualTo(expectedType)
    }

}