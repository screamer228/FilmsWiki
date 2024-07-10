package lilianisoft.test_task.filmswiki.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import lilianisoft.test_task.filmswiki.databinding.ItemMovieBinding
import lilianisoft.test_task.filmswiki.model.Movie

class MoviesAdapter(
    private var movieList: List<Movie> = listOf()
) : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

//    private var movieList: List<Movie> = listOf()

    inner class ViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Movie) {
            val posterUrl = getFullPosterUrl(item.poster_path)

            binding.movieImage.load(posterUrl)
            binding.movieTitle.text = item.title
            binding.movieReleaseDate.text = item.release_date
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMovieBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = movieList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    private fun getFullPosterUrl(posterPath: String): String {
        val baseUrl = "https://image.tmdb.org/t/p/"
        val size = "w400"
        return "$baseUrl$size$posterPath"
    }

//    fun updateList(newDataList: List<Movie>) {
//        val diffUtil = MoviesDiffUtil(offersList, newDataList)
//        val diffResult = DiffUtil.calculateDiff(diffUtil)
//        offersList = newDataList
//        diffResult.dispatchUpdatesTo(this)
//    }
}