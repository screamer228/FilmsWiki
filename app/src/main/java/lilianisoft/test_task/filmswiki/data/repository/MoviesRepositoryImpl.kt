package lilianisoft.test_task.filmswiki.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import lilianisoft.test_task.filmswiki.data.dto.MovieDto
import lilianisoft.test_task.filmswiki.data.dto.MoviesPageDto
import lilianisoft.test_task.filmswiki.data.mapper.MoviesEntityMapper
import lilianisoft.test_task.filmswiki.data.network.MoviesApi
import lilianisoft.test_task.filmswiki.domain.entity.MovieEntity
import lilianisoft.test_task.filmswiki.domain.entity.MoviesPageEntity
import lilianisoft.test_task.filmswiki.domain.repository.MoviesRepository
import retrofit2.Response
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val moviesApi: MoviesApi,
    private val moviesEntityMapper: MoviesEntityMapper
) : MoviesRepository {

    override suspend fun getPopularMoviesByPage(page: Int): Flow<MoviesPageEntity> = flow {
        val response: Response<MoviesPageDto> = moviesApi.getPopularMovies(page)
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                emit(moviesEntityMapper.mapDtoToUiPage(body))
            } else {
                throw IllegalStateException("Response body is null")
            }
        } else {
            throw Exception("API call failed with response code ${response.code()}")
        }
    }

    override suspend fun getMovieById(id: Int): Flow<MovieEntity> = flow {
        val response: Response<MovieDto> = moviesApi.getMovieById(id)
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                emit(moviesEntityMapper.mapDtoToUi(body))
            } else {
                throw IllegalStateException("Response body is null")
            }
        } else {
            throw Exception("API call failed with response code ${response.code()}")
        }
    }
}