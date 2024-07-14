package lilianisoft.test_task.filmswiki.domain.repository

import kotlinx.coroutines.flow.Flow
import lilianisoft.test_task.filmswiki.domain.entity.MovieEntity
import lilianisoft.test_task.filmswiki.domain.entity.MoviesPageEntity

interface MoviesRepository {

    suspend fun getPopularMoviesByPage(page: Int): Flow<MoviesPageEntity>

    suspend fun getMovieById(id: Int): Flow<MovieEntity>
}