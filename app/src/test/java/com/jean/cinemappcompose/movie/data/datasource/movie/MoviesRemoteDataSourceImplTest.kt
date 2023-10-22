package com.jean.cinemappcompose.movie.data.datasource.movie

import com.google.common.truth.Truth
import com.jean.cinemappcompose.core.util.Constants.EMPTY_STRING
import com.jean.cinemappcompose.core.util.JsonFileLoader
import com.jean.cinemappcompose.core.util.MainCoroutineRule
import com.jean.cinemappcompose.core.util.TestConstants.SERVER_PORT
import com.jean.cinemappcompose.core.util.TestConstants.SLASH_SEPARATOR
import com.jean.cinemappcompose.core.util.createMoviesApiServiceMock
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.net.HttpURLConnection

@ExperimentalCoroutinesApi
class MoviesRemoteDataSourceImplTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private lateinit var mockWebServer: MockWebServer
    private lateinit var sut: MoviesRemoteDataSource

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.start(port = SERVER_PORT)
        val baseUrl = mockWebServer.url(path = SLASH_SEPARATOR).toString()
        sut = MoviesRemoteDataSourceImpl(
            createMoviesApiServiceMock(baseUrl),
            mainCoroutineRule.testDispatcher
        )
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `validate when call get in theater movies service, then returns a movie list`() = runTest {
        val expectedResponse = JsonFileLoader()
            .loadJsonString(file = "movie/get_movies_200.json") ?: EMPTY_STRING
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody(expectedResponse)
        )

        val currentResponse = sut.getInTheaterMovies()

        Truth.assertThat(currentResponse).isNotNull()
        Truth.assertThat(currentResponse).isNotEmpty()
        Truth.assertThat(currentResponse[0].id).isNotNull()
        Truth.assertThat(currentResponse[0].title).isNotEmpty()
        Truth.assertThat(currentResponse[0].overview).isNotEmpty()
        Truth.assertThat(currentResponse[0].posterPath).isNotEmpty()
        Truth.assertThat(currentResponse[0].releaseDate).isNotEmpty()
        Truth.assertThat(currentResponse[0].voteAverage).isNotNull()
        Truth.assertThat(currentResponse[0].genreIds).isNotNull()
        Truth.assertThat(currentResponse[0].genreIds).isNotEmpty()
    }

    @Test
    fun `validate when call get upcoming movies service, then returns a movie list`() = runTest {
        val expectedResponse = JsonFileLoader()
            .loadJsonString(file = "movie/get_movies_200.json") ?: EMPTY_STRING
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody(expectedResponse)
        )

        val currentResponse = sut.getUpcomingMovies()

        Truth.assertThat(currentResponse).isNotNull()
        Truth.assertThat(currentResponse).isNotEmpty()
        Truth.assertThat(currentResponse[0].id).isNotNull()
        Truth.assertThat(currentResponse[0].title).isNotEmpty()
        Truth.assertThat(currentResponse[0].overview).isNotEmpty()
        Truth.assertThat(currentResponse[0].posterPath).isNotEmpty()
        Truth.assertThat(currentResponse[0].releaseDate).isNotEmpty()
        Truth.assertThat(currentResponse[0].voteAverage).isNotNull()
        Truth.assertThat(currentResponse[0].genreIds).isNotNull()
        Truth.assertThat(currentResponse[0].genreIds).isNotEmpty()
    }

    @Test
    fun `validate when call in theater movies with a invalid value, then return error 401`() = runTest {
        val expectedResponse = JsonFileLoader()
            .loadJsonString(file = "movie/get_movies_401.json") ?: EMPTY_STRING
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_UNAUTHORIZED)
                .setBody(expectedResponse)
        )

        val currentResponse = sut.getInTheaterMovies()

        Truth.assertThat(currentResponse).isNotNull()
        Truth.assertThat(currentResponse).isEmpty()
    }

    @Test
    fun `validate when call upcoming movies with a invalid value, then return error 401`() = runTest {
        val expectedResponse = JsonFileLoader()
            .loadJsonString(file = "movie/get_movies_401.json") ?: EMPTY_STRING
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_UNAUTHORIZED)
                .setBody(expectedResponse)
        )

        val currentResponse = sut.getUpcomingMovies()

        Truth.assertThat(currentResponse).isNotNull()
        Truth.assertThat(currentResponse).isEmpty()
    }

}