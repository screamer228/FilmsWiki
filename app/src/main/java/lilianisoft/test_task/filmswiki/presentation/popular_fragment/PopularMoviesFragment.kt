package lilianisoft.test_task.filmswiki.presentation.popular_fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import lilianisoft.test_task.filmswiki.R
import lilianisoft.test_task.filmswiki.data.RetrofitInstance
import lilianisoft.test_task.filmswiki.databinding.FragmentPopularMoviesBinding
import lilianisoft.test_task.filmswiki.host.API_KEY
import lilianisoft.test_task.filmswiki.host.HostActivity
import lilianisoft.test_task.filmswiki.model.Movie
import lilianisoft.test_task.filmswiki.model.MovieResponse
import lilianisoft.test_task.filmswiki.presentation.adapter.MoviesAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class PopularMoviesFragment : Fragment() {

    private lateinit var _binding: FragmentPopularMoviesBinding
    private val binding get() = _binding
    private lateinit var adapter: MoviesAdapter
//
//    @Inject
//    lateinit var viewModelFactory: PopularMoviesViewModelFactory
//    private lateinit var viewModel: PopularMoviesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        (requireActivity().applicationContext as App).appComponent.injectMainFragment(this)
//        viewModel =
//            ViewModelProvider(requireActivity(), viewModelFactory)[PopularMoviesViewModel::class.java]

        _binding = FragmentPopularMoviesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = MoviesAdapter()

//        binding.recyclerViewPopularMovies.adapter = adapter

        fetchPopularMovies()
    }

    private fun fetchPopularMovies() {
        val call = RetrofitInstance.api.getPopularMovies(API_KEY)
        call.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful) {
                    Toast.makeText(
                        requireContext(),
                        "Удачный запрос: ${response.code()}",
                        Toast.LENGTH_LONG
                    ).show()
                    Log.d("retrofit test", "${response.code()}\n ${response.message()}")
                    val movies = response.body()?.results
                    adapter = MoviesAdapter(movies!!)
                    binding.recyclerViewPopularMovies.adapter = adapter
                    adapter.notifyDataSetChanged()
                    // Обработка списка фильмов, например, обновление UI
//                    binding.textView1.text = movies?.first()?.title
//                    binding.textView2.text = movies?.first()?.overview
//                    binding.textView3.text = movies?.first()?.poster_path
//                    binding.textView4.text = movies?.first()?.id.toString()
                }
                Log.d("retrofit test", "${response.code()}\n ${response.errorBody()}")
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                // Обработка ошибки
                Toast.makeText(
                    requireContext(),
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
                        requireContext(),
                        "Удачный запрос: ${response.code()}",
                        Toast.LENGTH_LONG
                    ).show()
                    Log.d("retrofit test", "${response.code()}\n ${response.message()}")
                    val movie = response.body()
                    // Обработка списка фильмов, например, обновление UI
//                    binding.textView5.text = movie?.title
//                    binding.textView6.text = movie?.overview
//                    binding.textView7.text = movie?.release_date
//                    binding.textView8.text = movie?.id.toString()
//
//                    val posterUrl = getFullPosterUrl(movie!!.poster_path)
//                    binding.imageView.load(posterUrl)
                }
                Log.d("retrofit test", "${response.code()}\n ${response.errorBody()}")
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                // Обработка ошибки
                Toast.makeText(
                    requireContext(),
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