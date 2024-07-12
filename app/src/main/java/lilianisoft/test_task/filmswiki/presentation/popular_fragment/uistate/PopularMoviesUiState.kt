package lilianisoft.test_task.filmswiki.presentation.popular_fragment.uistate

import lilianisoft.test_task.filmswiki.presentation.model.Movie

data class PopularMoviesUiState(
    val movieList: List<Movie> = listOf(),
    val page: Int = 1
//    val navigation
)
