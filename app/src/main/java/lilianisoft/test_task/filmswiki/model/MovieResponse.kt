package lilianisoft.test_task.filmswiki.model

data class MovieResponse(
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)
