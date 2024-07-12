package lilianisoft.test_task.filmswiki.di

import dagger.Module
import dagger.Provides
import lilianisoft.test_task.filmswiki.data.repository.MoviesRepository
import lilianisoft.test_task.filmswiki.presentation.detail_fragment.viewmodel.DetailMovieViewModelFactory
import lilianisoft.test_task.filmswiki.presentation.mapper.MoviesMapper
import lilianisoft.test_task.filmswiki.presentation.popular_fragment.viewmodel.PopularMoviesViewModelFactory

@Module
class AppModule {

    @Provides
    fun provideDetailMovieViewModelFactory(
        moviesRepository: MoviesRepository
    ): DetailMovieViewModelFactory {
        return DetailMovieViewModelFactory(
            moviesRepository,
            moviesMapper = MoviesMapper()
        )
    }

    @Provides
    fun providePopularMoviesViewModelFactory(
        moviesRepository: MoviesRepository
    ): PopularMoviesViewModelFactory {
        return PopularMoviesViewModelFactory(
            moviesRepository,
            moviesMapper = MoviesMapper()
        )
    }
}