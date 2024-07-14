package lilianisoft.test_task.filmswiki.data.dto

import com.google.gson.annotations.SerializedName

data class MoviesPageDto(
    val page: Int,
    @SerializedName("results") val movieList: List<MovieDto>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int
)
