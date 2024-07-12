package lilianisoft.test_task.filmswiki.data.repository

import kotlinx.coroutines.flow.Flow
import lilianisoft.test_task.filmswiki.data.dto.MovieDto
import lilianisoft.test_task.filmswiki.data.dto.MoviesPageDto

interface MoviesRepository {

    suspend fun getPopularMoviesByPage(page: Int) : Flow<MoviesPageDto>

    suspend fun getMovieById(id: Int) : Flow<MovieDto>

}