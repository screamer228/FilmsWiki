package lilianisoft.test_task.filmswiki.presentation.popular_fragment.viewmodel

import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import lilianisoft.test_task.filmswiki.data.repository.MoviesRepository
import lilianisoft.test_task.filmswiki.presentation.mapper.MoviesMapper
import lilianisoft.test_task.filmswiki.presentation.popular_fragment.uistate.PopularMoviesUiState
import java.net.ConnectException
import javax.inject.Inject

class PopularMoviesViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository,
    private val moviesMapper: MoviesMapper
) : ViewModel() {

    private val _uiState = MutableStateFlow(PopularMoviesUiState())
    val uiState: StateFlow<PopularMoviesUiState> = _uiState.asStateFlow()

    private val _events = MutableSharedFlow<UiEvent>()
    val events = _events.asSharedFlow()

    private val _navigationEvents = MutableSharedFlow<NavigationEvent>()
    val navigationEvents = _navigationEvents.asSharedFlow()

    init {
        getMoviesPage()
    }

    private fun getMoviesPage() {
        viewModelScope.launch {
            moviesRepository.getPopularMoviesByPage(1)
                .flowOn(Dispatchers.IO)
                .map { moviesMapper.mapDtoToUiPage(it) }
                .catch { e ->
                    handleNetworkError(e)
                }
                .collect { mappedMoviePage ->
                    _uiState.value = _uiState.value.copy(
                        movieList = mappedMoviePage.movieList,
                        page = mappedMoviePage.page
                    )
                }
        }
    }

    private fun handleNetworkError(exception: Throwable) {
        if (exception is ConnectException) {
            _events.emit(UiEvent.ShowToast("Please enable VPN to access the API"))
        } else {
            exception.printStackTrace()
        }
    }

//    fun setNavigationState(event: CountrySelectedNavigationEvent) {
//        _uiState.value = _uiState.value.copy(navigation = event)
//    }
}