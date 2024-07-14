package lilianisoft.test_task.filmswiki.data.dto

import com.google.gson.annotations.SerializedName

data class MovieDto(
    val id: Int,
    val title: String,
    @SerializedName("release_date") val releaseDate: String,
    val overview: String,
    @SerializedName("poster_path") val posterPath: String
)