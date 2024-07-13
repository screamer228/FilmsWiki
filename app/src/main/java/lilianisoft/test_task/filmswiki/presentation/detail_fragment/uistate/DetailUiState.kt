package lilianisoft.test_task.filmswiki.presentation.detail_fragment.uistate

import lilianisoft.test_task.filmswiki.presentation.model.Movie

data class DetailUiState(
    val movie: Movie = Movie(),
    val isFavorite: Boolean = false,
    val isLoading: Boolean = true
)
