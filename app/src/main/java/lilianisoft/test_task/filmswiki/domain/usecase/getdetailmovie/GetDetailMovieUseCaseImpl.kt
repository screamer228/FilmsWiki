package lilianisoft.test_task.filmswiki.domain.usecase.getdetailmovie

import kotlinx.coroutines.flow.Flow
import lilianisoft.test_task.filmswiki.domain.entity.MovieEntity
import lilianisoft.test_task.filmswiki.domain.repository.MoviesRepository
import lilianisoft.test_task.filmswiki.domain.entity.MoviesPageEntity
import javax.inject.Inject

class GetDetailMovieUseCaseImpl @Inject constructor(
    private val moviesRepository: MoviesRepository
) : GetDetailMovieUseCase {

    override suspend fun execute(movieId: Int): Flow<MovieEntity> {
        return moviesRepository.getMovieById(movieId)
    }
}