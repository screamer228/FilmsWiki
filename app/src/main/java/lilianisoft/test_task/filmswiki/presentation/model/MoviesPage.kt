package lilianisoft.test_task.filmswiki.presentation.model

data class MoviesPage(
    val page: Int,
    val movieList: List<Movie>,
    val totalPages: Int,
    val totalResults: Int
)
