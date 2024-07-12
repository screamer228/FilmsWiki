package lilianisoft.test_task.filmswiki.presentation.search_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import lilianisoft.test_task.filmswiki.R
import lilianisoft.test_task.filmswiki.app.App

class SearchMoviesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (requireActivity().applicationContext as App).appComponent.injectSearchMoviesFragment(this)

        return inflater.inflate(R.layout.fragment_search_movies, container, false)
    }
}