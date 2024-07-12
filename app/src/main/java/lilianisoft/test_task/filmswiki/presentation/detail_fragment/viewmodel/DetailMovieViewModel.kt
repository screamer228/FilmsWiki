package lilianisoft.test_task.filmswiki.presentation.detail_fragment.viewmodel

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
import lilianisoft.test_task.filmswiki.data.repository.MoviesRepository
import lilianisoft.test_task.filmswiki.presentation.detail_fragment.uievent.DetailUiEvent
import lilianisoft.test_task.filmswiki.presentation.detail_fragment.uistate.DetailUiState
import lilianisoft.test_task.filmswiki.presentation.mapper.MoviesMapper
import lilianisoft.test_task.filmswiki.presentation.navigation.NavigationEvent
import lilianisoft.test_task.filmswiki.presentation.popular_fragment.uistate.PopularUiState
import java.net.ConnectException
import javax.inject.Inject

class DetailMovieViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository,
    private val moviesMapper: MoviesMapper
) : ViewModel() {

    private val _uiState = MutableStateFlow(DetailUiState())
    val uiState: StateFlow<DetailUiState> = _uiState.asStateFlow()

    private val _events = MutableSharedFlow<DetailUiEvent>()
    val events = _events.asSharedFlow()

    private val _navigationEvents = MutableSharedFlow<NavigationEvent>()
    val navigationEvents = _navigationEvents.asSharedFlow()

    fun getMovieById(movieId: Int) {
        viewModelScope.launch {
            moviesRepository.getMovieById(movieId)
                .flowOn(Dispatchers.IO)
                .map { moviesMapper.mapDtoToUi(it) }
                .catch { e ->
                    handleNetworkError(e)
                }
                .collect { mappedMoviePage ->
                    _uiState.value = _uiState.value.copy(
                        movie = mappedMoviePage
                    )
                }
        }
    }

    private suspend fun handleNetworkError(exception: Throwable) {
        if (exception is ConnectException) {
            _events.emit(DetailUiEvent.ShowToast("Please enable VPN to access the API"))
        } else {
            exception.printStackTrace()
        }
    }

    fun onBackClicked() {
        viewModelScope.launch {
            _navigationEvents.emit(NavigationEvent.Back())
        }
    }

//    fun setNavigationState(event: CountrySelectedNavigationEvent) {
//        _uiState.value = _uiState.value.copy(navigation = event)
//    }
}