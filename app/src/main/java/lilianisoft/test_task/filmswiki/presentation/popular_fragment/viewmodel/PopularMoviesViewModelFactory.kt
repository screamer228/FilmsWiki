package lilianisoft.test_task.filmswiki.presentation.popular_fragment.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import lilianisoft.test_task.filmswiki.data.repository.MoviesRepository
import lilianisoft.test_task.filmswiki.presentation.mapper.MoviesMapper

class PopularMoviesViewModelFactory(
    private val moviesRepository: MoviesRepository,
    private val moviesMapper: MoviesMapper
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PopularMoviesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PopularMoviesViewModel(
                moviesRepository,
                moviesMapper
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}