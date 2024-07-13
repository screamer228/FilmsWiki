package lilianisoft.test_task.filmswiki.domain.entity

data class MovieEntity(
    val id: Int,
    val title: String,
    val releaseDate: String,
    val overview: String,
    val posterPath: String
)