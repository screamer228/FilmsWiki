package lilianisoft.test_task.filmswiki.data

import lilianisoft.test_task.filmswiki.model.Movie
import lilianisoft.test_task.filmswiki.model.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TMDbApi {
    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String,
//        @Query("page") page: Int = 1
    ): Call<MovieResponse>

    @GET("movie/{movie_id}")
    fun getMovieById(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ): Call<Movie>
}