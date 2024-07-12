package lilianisoft.test_task.filmswiki.presentation.popular_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.launch
import lilianisoft.test_task.filmswiki.app.App
import lilianisoft.test_task.filmswiki.databinding.FragmentPopularMoviesBinding
import lilianisoft.test_task.filmswiki.presentation.navigation.NavigationEvent
import lilianisoft.test_task.filmswiki.presentation.popular_fragment.adapter.MoviesAdapter
import lilianisoft.test_task.filmswiki.presentation.popular_fragment.uievent.PopularUiEvent
import lilianisoft.test_task.filmswiki.presentation.popular_fragment.viewmodel.PopularMoviesViewModel
import lilianisoft.test_task.filmswiki.presentation.popular_fragment.viewmodel.PopularMoviesViewModelFactory
import javax.inject.Inject

class PopularMoviesFragment : Fragment() {

    private lateinit var _binding: FragmentPopularMoviesBinding
    private val binding get() = _binding
    private lateinit var adapter: MoviesAdapter

    @Inject
    lateinit var viewModelFactory: PopularMoviesViewModelFactory
    private lateinit var viewModel: PopularMoviesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (requireActivity().applicationContext as App).appComponent.injectPopularMoviesFragment(this)

        injectViewModel()

        _binding = FragmentPopularMoviesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = MoviesAdapter() {}

        // Подписка на события UI
        lifecycleScope.launch {
            viewModel.events.collect { event ->
                when (event) {
                    is PopularUiEvent.ShowToast -> {
                        Toast.makeText(requireContext(), event.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiState.collect { uiState ->
                adapter = MoviesAdapter(uiState.movieList) {
                    viewModel.onMovieClicked(it.id)
                }
                binding.recyclerViewPopularMovies.adapter = adapter
                adapter.notifyDataSetChanged()
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.navigationEvents.collect { event ->
                when (event) {
                    is NavigationEvent.ToMovieDetail -> {
                        val action =
                            PopularMoviesFragmentDirections.actionPopularMoviesFragmentToDetailMovieFragment(
                                event.movieId
                            )
                        findNavController().navigate(action)
                    }

                    else -> {}
                }
            }
        }
    }

    private fun injectViewModel() {
        viewModel = ViewModelProvider(
            requireActivity(),
            viewModelFactory
        )[PopularMoviesViewModel::class.java]
    }
}