package search.finder.searchmovies.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.api.load
import kotlinx.android.synthetic.main.fragment_movie_detail.*
import search.finder.searchmovies.R
import search.finder.searchmovies.databinding.FragmentMovieDetailBinding
import search.finder.searchmovies.model.*
import search.finder.searchmovies.view.contentprovider.ContactsFragment
import search.finder.searchmovies.view.map.MapsFragment
import search.finder.searchmovies.viewmodel.AppState
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
        viewModel.getLiveData().observe(viewLifecycleOwner, { renderData(it) })
        val movie = arguments?.getParcelable<MovieDTO>(KEY_MOVIE)
        movie?.let { viewModel.getMovieFromRemoteSource(movie.id, TMDB_API_KEY_VALUE, "ru-RU") }
        movie?.let { viewModel.getMovieCreditsFromRemoteSource(movie.id, TMDB_API_KEY_VALUE, "ru-RU")}
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Error -> Toast.makeText(
                requireActivity(),
                "Ошибка загрузки",
                Toast.LENGTH_SHORT
            ).show()
            is AppState.SuccessDetail -> setData(appState.dataMovie)
            is AppState.SuccessCredits -> setCredits(appState.dataMovie)
            else -> Toast.makeText(requireActivity(), "Ошибка загрузки", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setCredits(movie: MovieCreditsDTO) {
        binding.birthMap.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .setCustomAnimations(
                    R.anim.enter_fragment,
                    R.anim.exit_fragment,
                    R.anim.enter_fragment_in,
                    R.anim.exit_fragment_out
                )
                .add(R.id.fragment_container, MapsFragment.newInstance(Bundle().apply {
                    putParcelable(MapsFragment.KEY_MOVIE_DETAILS, movie)
                })).addToBackStack("")
                .commit()
        }
    }

    private fun setData(movie: MovieDetailsDTO) {
        viewModel.saveMovieHistoryToDb(movie)
        with(binding) {
            with(movie) {
                movieImgDetail.load("$TMDB_MOVIE_POSTER_URL$poster_path")
                movieTitleDetail.text = title
                movieDescDetail.text = overview
                movieReleaseYearDetail.text = release_date
                movieVotesAverageDetail.text = vote_average.toString()
                save_movie.setOnClickListener {
                    viewModel.saveFavoriteMovieToDb(movie)
                    Toast.makeText(requireContext(), "Добавлено в избранное", Toast.LENGTH_SHORT)
                        .show()
                }
                delete_movie.setOnClickListener {
                    viewModel.deleteFavoriteMovieFromDb(movie)
                    Toast.makeText(requireContext(), "Удалено из избранного", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }

    }
}
