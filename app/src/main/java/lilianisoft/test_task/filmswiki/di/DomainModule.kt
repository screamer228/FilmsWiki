package lilianisoft.test_task.filmswiki.di

import dagger.Module
import dagger.Provides
import lilianisoft.test_task.filmswiki.domain.repository.MoviesRepository
import lilianisoft.test_task.filmswiki.domain.usecase.getdetailmovie.GetDetailMovieUseCase
import lilianisoft.test_task.filmswiki.domain.usecase.getdetailmovie.GetDetailMovieUseCaseImpl
import lilianisoft.test_task.filmswiki.domain.usecase.getpopularmovies.GetPopularMoviesPageUseCase
import lilianisoft.test_task.filmswiki.domain.usecase.getpopularmovies.GetPopularMoviesPageUseCaseImpl

@Module
class DomainModule {

    @Provides
    fun provideGetDetailMovieUseCase(
        moviesRepository: MoviesRepository,
    ): GetDetailMovieUseCase {
        return GetDetailMovieUseCaseImpl(
            moviesRepository
        )
    }

    @Provides
    fun provideGetPopularMoviesPageUseCase(
        moviesRepository: MoviesRepository,
    ): GetPopularMoviesPageUseCase {
        return GetPopularMoviesPageUseCaseImpl(
            moviesRepository
        )
    }
}