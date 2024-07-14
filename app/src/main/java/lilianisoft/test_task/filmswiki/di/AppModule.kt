package lilianisoft.test_task.filmswiki.di

import dagger.Module
import dagger.Provides
import lilianisoft.test_task.filmswiki.domain.usecase.getdetailmovie.GetDetailMovieUseCase
import lilianisoft.test_task.filmswiki.domain.usecase.getpopularmovies.GetPopularMoviesPageUseCase
import lilianisoft.test_task.filmswiki.presentation.detail_fragment.viewmodel.DetailMovieViewModelFactory
import lilianisoft.test_task.filmswiki.presentation.mapper.MoviesMapper
import lilianisoft.test_task.filmswiki.presentation.popular_fragment.viewmodel.PopularMoviesViewModelFactory

@Module
class AppModule {

    @Provides
    fun provideDetailMovieViewModelFactory(
        getDetailMovieUseCase: GetDetailMovieUseCase
    ): DetailMovieViewModelFactory {
        return DetailMovieViewModelFactory(
            getDetailMovieUseCase,
            moviesMapper = MoviesMapper()
        )
    }

    @Provides
    fun providePopularMoviesViewModelFactory(
        getPopularMoviesPageUseCase: GetPopularMoviesPageUseCase
    ): PopularMoviesViewModelFactory {
        return PopularMoviesViewModelFactory(
            getPopularMoviesPageUseCase,
            moviesMapper = MoviesMapper()
        )
    }
}