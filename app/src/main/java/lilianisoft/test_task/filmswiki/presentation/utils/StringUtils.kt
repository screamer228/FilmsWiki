package lilianisoft.test_task.filmswiki.presentation.utils

object StringUtils {

    fun getYearFromDate(date: String): String = date.take(4)

    fun getFullPosterUrl(posterPath: String): String {
        val baseUrl = POSTER_BASE_URL
        val size = POSTER_SIZE
        return "$baseUrl$size$posterPath"
    }

    private const val POSTER_BASE_URL = "https://image.tmdb.org/t/p/"
    private const val POSTER_SIZE = "w400"
}