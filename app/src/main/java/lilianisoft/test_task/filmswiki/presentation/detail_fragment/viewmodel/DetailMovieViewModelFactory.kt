package lilianisoft.test_task.filmswiki.presentation.detail_fragment.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import lilianisoft.test_task.filmswiki.data.repository.MoviesRepository
import lilianisoft.test_task.filmswiki.presentation.mapper.MoviesMapper

class DetailMovieViewModelFactory(
    private val moviesRepository: MoviesRepository,
    private val moviesMapper: MoviesMapper
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailMovieViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DetailMovieViewModel(
                moviesRepository,
                moviesMapper
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}