package lilianisoft.test_task.filmswiki.presentation.popular_fragment.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import lilianisoft.test_task.filmswiki.domain.repository.MoviesRepository
import lilianisoft.test_task.filmswiki.domain.usecase.getpopularmovies.GetPopularMoviesPageUseCase
import lilianisoft.test_task.filmswiki.presentation.mapper.MoviesMapper

class PopularMoviesViewModelFactory(
    private val getPopularMoviesPageUseCase: GetPopularMoviesPageUseCase,
    private val moviesMapper: MoviesMapper
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PopularMoviesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PopularMoviesViewModel(
                getPopularMoviesPageUseCase,
                moviesMapper
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}