package lilianisoft.test_task.filmswiki.presentation.favorites_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import lilianisoft.test_task.filmswiki.R
import lilianisoft.test_task.filmswiki.app.App

class FavoriteMoviesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (requireActivity().applicationContext as App).appComponent.injectFavoriteMoviesFragment(this)

        return inflater.inflate(R.layout.fragment_favorite_movies, container, false)
    }
}