package lilianisoft.test_task.filmswiki.di

import dagger.Module
import dagger.Provides
import lilianisoft.test_task.filmswiki.data.repository.MoviesRepository
import lilianisoft.test_task.filmswiki.presentation.mapper.MoviesMapper
import lilianisoft.test_task.filmswiki.presentation.popular_fragment.viewmodel.PopularMoviesViewModelFactory

@Module
class AppModule {

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