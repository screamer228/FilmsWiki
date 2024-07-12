package lilianisoft.test_task.filmswiki.data.network

import lilianisoft.test_task.filmswiki.data.dto.MovieDto
import lilianisoft.test_task.filmswiki.data.dto.MoviesPageDto
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MoviesApi {
    @GET("movie/popular")
    suspend fun getPopularMovies(
//        @Query("api_key") apiKey: String,
        @Query("page") page: Int = 1,
    ): Response<MoviesPageDto>

    @GET("movie/{movie_id}")
    suspend fun getMovieById(
        @Path("movie_id") movieId: Int,
//        @Query("api_key") apiKey: String
    ): Response<MovieDto>
}