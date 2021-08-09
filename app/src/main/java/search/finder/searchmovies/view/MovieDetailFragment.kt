package search.finder.searchmovies.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import search.finder.searchmovies.databinding.FragmentMovieDetailBinding
import search.finder.searchmovies.model.Movie
import search.finder.searchmovies.model.MovieDTO


class MovieDetailFragment : Fragment() {

    companion object {
        const val KEY_MOVIE = "KEY"
        fun newInstance(bundle: Bundle): MovieDetailFragment {
            val fragment = MovieDetailFragment()
            fragment.arguments = bundle
            return fragment
        }
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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //val movie = arguments?.getParcelable(KEY_MOVIE) as? Movie
        val movie = arguments?.getParcelable<MovieDTO>(KEY_MOVIE)
        movie?.let { setData(movie) }
    }

    private fun setData(movie: MovieDTO) {
        with(binding) {
            with(movie) {
                movieTitleDetail.text = title
                movieDescDetail.text = originalTitle
                movieReleaseYearDetail.text = releaseDate?.toString() ?: ""
                movieVotesAverageDetail.text = voteCount?.toString() ?: "0.0"
            }
        }
    }
}