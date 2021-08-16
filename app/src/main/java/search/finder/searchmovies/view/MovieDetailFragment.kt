package search.finder.searchmovies.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.api.load
import search.finder.searchmovies.databinding.FragmentMovieDetailBinding
import search.finder.searchmovies.model.MovieDTO
import search.finder.searchmovies.model.MovieDetailsDTO
import search.finder.searchmovies.model.TMDB_API_KEY_VALUE
import search.finder.searchmovies.model.TMDB_MOVIE_POSTER_URL
import search.finder.searchmovies.viewmodel.DetailState
import search.finder.searchmovies.viewmodel.DetailViewModel


class MovieDetailFragment : Fragment() {

    companion object {
        const val KEY_MOVIE = "KEY"
        fun newInstance(bundle: Bundle): MovieDetailFragment {
            val fragment = MovieDetailFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    private val viewModel: DetailViewModel by lazy {
        ViewModelProvider(this).get(DetailViewModel::class.java)
    }

    private var _binding: FragmentMovieDetailBinding? = null
    private val binding: FragmentMovieDetailBinding
        get(): FragmentMovieDetailBinding {
            return _binding!!
        }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getLiveData().observe(viewLifecycleOwner, Observer { renderData(it) })
        val movie = arguments?.getParcelable<MovieDTO>(KEY_MOVIE)
        movie?.let {viewModel.getMovieFromRemoteSource(movie.id, TMDB_API_KEY_VALUE, "ru-RU")}
    }

    private fun renderData(appState: DetailState) {
        when (appState) {
            is DetailState.Error -> Toast.makeText(requireActivity(), "Ошибка загрузки", Toast.LENGTH_SHORT).show()
            is DetailState.Success -> setData(appState.dataMovie)
        }
    }

    private fun setData(movie: MovieDetailsDTO) {
        with(binding) {
            with(movie) {
                movieImgDetail.load(TMDB_MOVIE_POSTER_URL +poster_path)
                movieTitleDetail.text = title
                movieDescDetail.text = overview
                movieReleaseYearDetail.text = release_date
                movieVotesAverageDetail.text = vote_average.toString()
            }
        }
    }
}