package lilianisoft.test_task.filmswiki.domain.usecase.getdetailmovie

import kotlinx.coroutines.flow.Flow
import lilianisoft.test_task.filmswiki.domain.entity.MovieEntity

interface GetDetailMovieUseCase {

    suspend fun execute(movieId: Int): Flow<MovieEntity>
}