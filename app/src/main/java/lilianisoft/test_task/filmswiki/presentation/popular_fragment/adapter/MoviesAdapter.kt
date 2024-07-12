package lilianisoft.test_task.filmswiki.presentation.popular_fragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import lilianisoft.test_task.filmswiki.databinding.ItemMovieBinding
import lilianisoft.test_task.filmswiki.presentation.model.Movie

class MoviesAdapter(
    private var movieDtoList: List<Movie> = listOf(),
    private val onMovieClick: (Movie) -> Unit
) : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

//    private var movieList: List<Movie> = listOf()

    inner class ViewHolder(
        private val binding: ItemMovieBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Movie) {
            binding.movieImage.load(item.posterUrl)
            binding.movieTitle.text = item.title
            binding.movieReleaseDate.text = item.releaseYear
            binding.movieContainer.setOnClickListener {
                onMovieClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMovieBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = movieDtoList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return movieDtoList.size
    }

//    fun updateList(newDataList: List<Movie>) {
//        val diffUtil = MoviesDiffUtil(offersList, newDataList)
//        val diffResult = DiffUtil.calculateDiff(diffUtil)
//        offersList = newDataList
//        diffResult.dispatchUpdatesTo(this)
//    }
}