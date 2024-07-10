package lilianisoft.test_task.filmswiki.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import lilianisoft.test_task.filmswiki.R
import lilianisoft.test_task.filmswiki.databinding.FragmentPopularMoviesBinding
import lilianisoft.test_task.filmswiki.host.HostActivity
import lilianisoft.test_task.filmswiki.presentation.adapter.MoviesAdapter
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
        binding.recyclerViewPopularMovies.adapter = adapter

//        inputFilters()
//
//        observers()
    }
}