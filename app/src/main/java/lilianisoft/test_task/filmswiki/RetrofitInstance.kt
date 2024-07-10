package lilianisoft.test_task.filmswiki

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://api.themoviedb.org/3/"

    val api: TMDbApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)

            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TMDbApi::class.java)
    }
}