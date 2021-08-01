package search.finder.searchmovies.view

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import search.finder.searchmovies.R
import search.finder.searchmovies.databinding.FragmentMovieDetailBinding
import search.finder.searchmovies.model.Movie


class MovieDetailFragment : DialogFragment() {

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


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        val movie = arguments?.getParcelable(KEY_MOVIE) as? Movie
        if (movie != null) setData(movie)
        return binding.root
    }


    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val contentView: View = requireActivity().layoutInflater.inflate(R.layout.fragment_movie_detail, null)
        val builder = AlertDialog.Builder(requireActivity())
            .setView(contentView)

        return builder.create()
    }


    private fun setData(movie: Movie){
//        val title: TextView? = view?.findViewById(R.id.movie_title_detail)
//        val description: TextView? = view?.findViewById(R.id.movie_desc_detail)
//        val year: TextView? = view?.findViewById(R.id.movie_release_year_detail)
//        val vote: TextView? = view?.findViewById(R.id.movie_votes_average_detail)
//
//        title?.text = movie.title
//        description?.text = movie.movieDescription
//        year?.text = if (movie.releaseYear != null) movie.releaseYear.toString() else movie.expectData.toString()
//        vote?.text = if(movie.vote != null) movie.vote.toString() else "0.0"

        binding.movieTitleDetail.text = movie.title
        binding.movieDescDetail.text = movie.movieDescription
        binding.movieReleaseYearDetail.text = if (movie.releaseYear != null) movie.releaseYear.toString() else movie.expectData.toString()
        binding.movieVotesAverageDetail.text = if(movie.vote != null) movie.vote.toString() else "0.0"
    }
}