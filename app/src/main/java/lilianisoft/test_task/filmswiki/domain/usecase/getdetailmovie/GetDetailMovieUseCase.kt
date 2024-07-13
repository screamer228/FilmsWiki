package lilianisoft.test_task.filmswiki.domain.usecase.getdetailmovie

import kotlinx.coroutines.flow.Flow
import lilianisoft.test_task.filmswiki.domain.entity.MovieEntity
import lilianisoft.test_task.filmswiki.domain.entity.MoviesPageEntity

interface GetDetailMovieUseCase {

    suspend fun execute(page: Int) : Flow<MovieEntity>

}