package com.jean.cinemappcompose.movie.data.datasource.genre

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
class GenresRemoteDataSourceImplTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private lateinit var mockWebServer: MockWebServer
    private lateinit var sut: GenresRemoteDataSource

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.start(port = SERVER_PORT)
        val baseUrl = mockWebServer.url(path = SLASH_SEPARATOR).toString()
        sut = GenresRemoteDataSourceImpl(
            createMoviesApiServiceMock(baseUrl)
        )
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `validate when call get genre service, then returns a genre list`() = runTest {
        val expectedResponse = JsonFileLoader()
            .loadJsonString(file = "movie/get_genres_200.json") ?: EMPTY_STRING
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_OK)
                .setBody(expectedResponse)
        )

        val currentResponse = sut.getMovieGenres()

        Truth.assertThat(currentResponse).isNotNull()
        Truth.assertThat(currentResponse).isNotEmpty()
        Truth.assertThat(currentResponse[0].id).isNotNull()
        Truth.assertThat(currentResponse[0].name).isNotEmpty()
    }

    @Test
    fun `validate when call genre service with a invalid value, then return error 401`() = runTest {
        val expectedResponse = JsonFileLoader()
            .loadJsonString(file = "movie/get_genres_401.json") ?: EMPTY_STRING
        mockWebServer.enqueue(
            MockResponse()
                .setResponseCode(HttpURLConnection.HTTP_UNAUTHORIZED)
                .setBody(expectedResponse)
        )

        val currentResponse = sut.getMovieGenres()

        Truth.assertThat(currentResponse).isNotNull()
        Truth.assertThat(currentResponse).isEmpty()
    }

}