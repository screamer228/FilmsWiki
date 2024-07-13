package lilianisoft.test_task.filmswiki.presentation.popular_fragment.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import lilianisoft.test_task.filmswiki.domain.repository.MoviesRepository
import lilianisoft.test_task.filmswiki.domain.usecase.getpopularmovies.GetPopularMoviesPageUseCase
import lilianisoft.test_task.filmswiki.presentation.mapper.MoviesMapper
import lilianisoft.test_task.filmswiki.presentation.navigation.NavigationEvent
import lilianisoft.test_task.filmswiki.presentation.popular_fragment.uievent.PopularUiEvent
import lilianisoft.test_task.filmswiki.presentation.popular_fragment.uistate.PopularUiState
import java.net.ConnectException
import javax.inject.Inject

class PopularMoviesViewModel @Inject constructor(
    private val getPopularMoviesPageUseCase: GetPopularMoviesPageUseCase,
    private val moviesMapper: MoviesMapper
) : ViewModel() {

    private val _uiState = MutableStateFlow(PopularUiState())
    val uiState: StateFlow<PopularUiState> = _uiState.asStateFlow()

    private val _events = MutableSharedFlow<PopularUiEvent>()
    val events = _events.asSharedFlow()

    private val _navigationEvents = MutableSharedFlow<NavigationEvent>()
    val navigationEvents = _navigationEvents.asSharedFlow()

    init {
        getMoviesPage()
    }

    private fun getMoviesPage() {
        viewModelScope.launch {
            getPopularMoviesPageUseCase.execute(1)
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

    private suspend fun handleNetworkError(exception: Throwable) {
        if (exception is ConnectException) {
            _events.emit(PopularUiEvent.ShowToast("Please enable VPN to access the API"))
        } else {
            exception.printStackTrace()
        }
    }

    fun onMovieClicked(movieId: Int) {
        viewModelScope.launch {
            _navigationEvents.emit(NavigationEvent.ToMovieDetail(movieId))
        }
    }

//    fun setNavigationState(event: CountrySelectedNavigationEvent) {
//        _uiState.value = _uiState.value.copy(navigation = event)
//    }
}