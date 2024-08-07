package lilianisoft.test_task.filmswiki.presentation.popular_fragment.uistate

import lilianisoft.test_task.filmswiki.presentation.model.Movie

data class PopularUiState(
    val movieList: List<Movie> = listOf(),
    val page: Int = 1,
    val totalPages: Int = 1,
    val isLoading: Boolean = true
)
