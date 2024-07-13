package lilianisoft.test_task.filmswiki.presentation.detail_fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import kotlinx.coroutines.launch
import lilianisoft.test_task.filmswiki.app.App
import lilianisoft.test_task.filmswiki.databinding.FragmentDetailMovieBinding
import lilianisoft.test_task.filmswiki.presentation.detail_fragment.uievent.DetailUiEvent
import lilianisoft.test_task.filmswiki.presentation.detail_fragment.viewmodel.DetailMovieViewModel
import lilianisoft.test_task.filmswiki.presentation.detail_fragment.viewmodel.DetailMovieViewModelFactory
import lilianisoft.test_task.filmswiki.presentation.model.Movie
import lilianisoft.test_task.filmswiki.presentation.navigation.NavigationEvent
import javax.inject.Inject

class DetailMovieFragment : Fragment() {

    private val args: DetailMovieFragmentArgs by navArgs()
    private lateinit var _binding: FragmentDetailMovieBinding
    private val binding get() = _binding

    @Inject
    lateinit var viewModelFactory: DetailMovieViewModelFactory
    private lateinit var viewModel: DetailMovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (requireActivity().applicationContext as App).appComponent.injectDetailMovieFragment(this)

        injectViewModel()

        _binding = FragmentDetailMovieBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getMovieById(args.movieId)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiState.collect { uiState ->

                setContent(uiState.movie)

                when (uiState.isLoading) {
                    true -> {
                        binding.detailContentContainer.visibility = View.GONE
                        binding.detailLoading.visibility = View.VISIBLE
                    }

                    false -> {
                        binding.detailLoading.visibility = View.GONE
                        binding.detailContentContainer.visibility = View.VISIBLE
                    }
                }
            }
        }

        // Подписка на события UI
        lifecycleScope.launch {
            viewModel.events.collect { event ->
                when (event) {
                    is DetailUiEvent.ShowToast -> {
                        Toast.makeText(requireContext(), event.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.navigationEvents.collect { event ->
                when (event) {
                    is NavigationEvent.Back -> {
                        findNavController().popBackStack()
                    }

                    else -> {}
                }
            }
        }
    }

    private fun setContent(movie: Movie) {
        binding.detailPoster.load(movie.posterUrl)
        binding.detailTitle.text = movie.title
        binding.detailReleaseYear.text = movie.releaseYear
        binding.detailOverview.text = movie.overview
    }

    private fun injectViewModel() {
        viewModel = ViewModelProvider(
            requireActivity(),
            viewModelFactory
        )[DetailMovieViewModel::class.java]
    }
}