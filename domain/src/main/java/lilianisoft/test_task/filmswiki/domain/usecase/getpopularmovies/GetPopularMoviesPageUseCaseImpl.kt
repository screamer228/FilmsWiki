package lilianisoft.test_task.filmswiki.domain.usecase.getpopularmovies

import kotlinx.coroutines.flow.Flow
import lilianisoft.test_task.filmswiki.domain.repository.MoviesRepository
import lilianisoft.test_task.filmswiki.domain.entity.MoviesPageEntity
import javax.inject.Inject

class GetPopularMoviesPageUseCaseImpl @Inject constructor(
    private val moviesRepository: MoviesRepository
) : GetPopularMoviesPageUseCase {

    override suspend fun execute(page: Int): Flow<MoviesPageEntity> {
        return moviesRepository.getPopularMoviesByPage(page)
    }
}