package lilianisoft.test_task.filmswiki.domain.usecase.getpopularmovies

import kotlinx.coroutines.flow.Flow
import lilianisoft.test_task.filmswiki.domain.entity.MoviesPageEntity

interface GetPopularMoviesPageUseCase {

    suspend fun execute(page: Int) : Flow<MoviesPageEntity>

}