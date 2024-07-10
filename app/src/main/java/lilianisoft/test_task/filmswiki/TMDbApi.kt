package lilianisoft.test_task.filmswiki

import lilianisoft.test_task.filmswiki.model.Movie
import lilianisoft.test_task.filmswiki.model.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface TMDbApi {
    @GET("movie/popular")
    fun getPopularMovies(
//        @Header("Authorization") apiKey: String,
        @Query("api_key") apiKey: String,
//        @Query("language") language: String = "en-US",
//        @Query("page") page: Int = 1
    ): Call<MovieResponse>

    @GET("movie/{movie_id}")
    fun getMovieById(
//        @Header("Authorization") apiKey: String,
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
//        @Query("language") language: String = "en-US",
//        @Query("page") page: Int = 1
    ): Call<Movie>
}