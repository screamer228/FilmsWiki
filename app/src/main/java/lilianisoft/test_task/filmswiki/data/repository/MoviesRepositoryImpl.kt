package lilianisoft.test_task.filmswiki.data.repository

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import lilianisoft.test_task.filmswiki.data.dto.MovieDto
import lilianisoft.test_task.filmswiki.data.dto.MoviesPageDto
import lilianisoft.test_task.filmswiki.data.network.MoviesApi
import retrofit2.Response
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val moviesApi: MoviesApi
) : MoviesRepository {

    override suspend fun getPopularMoviesByPage(page: Int): Flow<MoviesPageDto> = flow {
        val response: Response<MoviesPageDto> = moviesApi.getPopularMovies(page)
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                emit(body)
            } else {
                throw IllegalStateException("Response body is null")
            }
        } else {
            throw Exception("API call failed with response code ${response.code()}")
        }
    }

    override suspend fun getMovieById(id: Int): MovieDto {
        val response = moviesApi.getMovieById(id)
        if (response.isSuccessful) {
            return response.body()!!
        }
        return response.body()!!
    }
}