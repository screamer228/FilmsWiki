package lilianisoft.test_task.filmswiki

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import coil.load
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlinx.coroutines.launch
import lilianisoft.test_task.filmswiki.databinding.ActivityHostBinding
import lilianisoft.test_task.filmswiki.model.Movie
import lilianisoft.test_task.filmswiki.model.MovieResponse

class HostActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHostBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityHostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fetchPopularMovies()

        fetchMovieById()
    }

    private fun fetchPopularMovies() {
        val call = RetrofitInstance.api.getPopularMovies(API_KEY)
        call.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful) {
                    Toast.makeText(
                        this@HostActivity,
                        "Удачный запрос: ${response.code()}",
                        Toast.LENGTH_LONG
                    ).show()
                    Log.d("retrofit test", "${response.code()}\n ${response.message()}")
                    val movies = response.body()?.results
                    // Обработка списка фильмов, например, обновление UI
                    binding.textView1.text = movies?.first()?.title
                    binding.textView2.text = movies?.first()?.overview
                    binding.textView3.text = movies?.first()?.poster_path
                    binding.textView4.text = movies?.first()?.id.toString()
                }
                Log.d("retrofit test", "${response.code()}\n ${response.errorBody()}")
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                // Обработка ошибки
                Toast.makeText(
                    this@HostActivity,
                    "Неудачный запрос: ${t.message}",
                    Toast.LENGTH_LONG
                ).show()
                Log.d("retrofit test", "${t.message}\n${t.localizedMessage}\n${t.cause}")
            }
        })
    }

    private fun fetchMovieById() {
        val call = RetrofitInstance.api.getMovieById(1022789, API_KEY)
        call.enqueue(object : Callback<Movie> {
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                if (response.isSuccessful) {
                    Toast.makeText(
                        this@HostActivity,
                        "Удачный запрос: ${response.code()}",
                        Toast.LENGTH_LONG
                    ).show()
                    Log.d("retrofit test", "${response.code()}\n ${response.message()}")
                    val movie = response.body()
                    // Обработка списка фильмов, например, обновление UI
                    binding.textView5.text = movie?.title
                    binding.textView6.text = movie?.overview
                    binding.textView7.text = movie?.poster_path
                    binding.textView8.text = movie?.id.toString()

                    val posterUrl = getFullPosterUrl(movie!!.poster_path)
                    binding.imageView.load(posterUrl)
                }
                Log.d("retrofit test", "${response.code()}\n ${response.errorBody()}")
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                // Обработка ошибки
                Toast.makeText(
                    this@HostActivity,
                    "Неудачный запрос: ${t.message}",
                    Toast.LENGTH_LONG
                ).show()
                Log.d("retrofit test", "${t.message}\n${t.localizedMessage}\n${t.cause}")
            }
        })
    }

    private fun getFullPosterUrl(posterPath: String): String {
        val baseUrl = "https://image.tmdb.org/t/p/"
        val size = "w500"
        return "$baseUrl$size$posterPath"
    }
}

const val API_KEY = "d483acb9b6f697c31f603b7e6e2e2722"