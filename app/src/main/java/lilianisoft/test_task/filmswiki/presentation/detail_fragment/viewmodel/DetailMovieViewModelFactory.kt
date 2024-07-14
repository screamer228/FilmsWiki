package lilianisoft.test_task.filmswiki.presentation.detail_fragment.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import lilianisoft.test_task.filmswiki.domain.usecase.getdetailmovie.GetDetailMovieUseCase
import lilianisoft.test_task.filmswiki.presentation.mapper.MoviesMapper

class DetailMovieViewModelFactory(
    private val getDetailMovieUseCase: GetDetailMovieUseCase,
    private val moviesMapper: MoviesMapper
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailMovieViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return DetailMovieViewModel(
                getDetailMovieUseCase,
                moviesMapper
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}