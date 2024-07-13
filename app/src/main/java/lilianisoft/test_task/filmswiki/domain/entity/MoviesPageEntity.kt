package lilianisoft.test_task.filmswiki.domain.entity

data class MoviesPageEntity(
    val page: Int,
    val movieList: List<MovieEntity>,
    val totalPages: Int,
    val totalResults: Int
)