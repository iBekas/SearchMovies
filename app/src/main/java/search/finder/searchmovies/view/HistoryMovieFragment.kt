package search.finder.searchmovies.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import search.finder.searchmovies.R
import search.finder.searchmovies.databinding.HistoryMovieFragmentBinding
import search.finder.searchmovies.model.MovieDetailsDTO
import search.finder.searchmovies.utils.convertMovieDetailDtoToMovieDto
import search.finder.searchmovies.view.adapter.HistoryAndFavoriteAdapter
import search.finder.searchmovies.view.adapter.OnHistoryOrFavoriteItemClick
import search.finder.searchmovies.viewmodel.AppState
import search.finder.searchmovies.viewmodel.HistoryAndFavoriteMovieViewModel

class HistoryMovieFragment : Fragment() {

    private val historyAndFavoriteAdapter = HistoryAndFavoriteAdapter(object :
        OnHistoryOrFavoriteItemClick {
        override fun onItemHistoryClick(movie: MovieDetailsDTO) {
            activity?.supportFragmentManager?.apply {
                beginTransaction()
                    .setCustomAnimations(R.anim.enter_fragment, R.anim.exit_fragment, R.anim.enter_fragment_in, R.anim.exit_fragment_out)
                    .add(
                        R.id.fragment_container,
                        MovieDetailFragment.newInstance(Bundle().apply {
                            putParcelable(MovieDetailFragment.KEY_MOVIE, convertMovieDetailDtoToMovieDto(movie))
                        })
                    )
                    .addToBackStack("")
                    .commitAllowingStateLoss()
            }
        }
    })

    companion object {
        fun newInstance() = HistoryMovieFragment()
    }

    private val viewModelAndFavorite: HistoryAndFavoriteMovieViewModel by lazy { ViewModelProvider(this).get(HistoryAndFavoriteMovieViewModel::class.java) }
    private var _binding: HistoryMovieFragmentBinding? = null
    private val binding: HistoryMovieFragmentBinding
        get(): HistoryMovieFragmentBinding {
            return _binding!!
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = HistoryMovieFragmentBinding.inflate(inflater, container, false)
        setupRecyclerView()
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        historyAndFavoriteAdapter.removeListener()
        viewModelAndFavorite.deleteMoviesHistory()
    }

    private fun setupRecyclerView() {
        val layoutManagerHistory =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            binding.rvMoviesHistory.layoutManager = layoutManagerHistory


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModelAndFavorite.getLiveData().observe(viewLifecycleOwner, {renderData(it)})
        viewModelAndFavorite.getAllMoviesHistory()
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Error -> Toast.makeText(requireActivity(), "Ошибка загрузки", Toast.LENGTH_SHORT).show()
            is AppState.SuccessHistory -> {
                with(binding) {
                    rvMoviesHistory.adapter = historyAndFavoriteAdapter
                    historyAndFavoriteAdapter.setMovies(appState.dataMovies)
                }
            }
            else -> Toast.makeText(requireActivity(), "Ошибка загрузки", Toast.LENGTH_SHORT).show()
        }
    }

}