package search.finder.searchmovies.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import search.finder.searchmovies.R
import search.finder.searchmovies.databinding.MainFragmentBinding
import search.finder.searchmovies.model.MovieDTO
import search.finder.searchmovies.model.TMDB_API_KEY_VALUE
import search.finder.searchmovies.view.adapter.NowPlayingAdapter
import search.finder.searchmovies.view.adapter.OnItemViewClickListener
import search.finder.searchmovies.view.adapter.UpcomingAdapter
import search.finder.searchmovies.viewmodel.AppState
import search.finder.searchmovies.viewmodel.MainViewModel

const val ACTION_SEND_MOVIE_TITLE = "SEND_MOVIE_TITLE"
const val SEND_MOVIE_TITLE = "MOVIE TITLE"

class MainFragment : Fragment() {

    private val nowPlayingAdapter: NowPlayingAdapter =
        NowPlayingAdapter(object : OnItemViewClickListener {
            override fun onItemViewClick(movie: MovieDTO) {
                activity?.supportFragmentManager?.apply {
                    beginTransaction()
                        .setCustomAnimations(R.anim.enter_fragment, R.anim.exit_fragment, R.anim.enter_fragment_in, R.anim.exit_fragment_out)
                        .add(
                            R.id.fragment_container,
                            MovieDetailFragment.newInstance(Bundle().apply {
                                putParcelable(MovieDetailFragment.KEY_MOVIE, movie)
                            })
                        )
                        .addToBackStack("")
                        .commitAllowingStateLoss()
                }
                val movieTitleToSend = movie.title
                val intent = Intent()
                intent.action = ACTION_SEND_MOVIE_TITLE
                intent.putExtra(SEND_MOVIE_TITLE, movieTitleToSend)
                intent.addFlags(Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP)
                context?.let { it.sendBroadcast(intent) }
            }
        })

    private val upcomingAdapter: UpcomingAdapter = UpcomingAdapter(object :
        OnItemViewClickListener {
        override fun onItemViewClick(movie: MovieDTO) {
            activity?.supportFragmentManager?.apply {
                beginTransaction()
                    .setCustomAnimations(R.anim.enter_fragment, R.anim.exit_fragment, R.anim.enter_fragment_in, R.anim.exit_fragment_out)
                    .add(
                        R.id.fragment_container,
                        MovieDetailFragment.newInstance(Bundle().apply {
                            putParcelable(MovieDetailFragment.KEY_MOVIE, movie)
                        })
                    )
                    .addToBackStack("")
                    .commitAllowingStateLoss()
            }
        }
    })


    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    private var _binding: MainFragmentBinding? = null
    private val binding: MainFragmentBinding
        get(): MainFragmentBinding {
            return _binding!!
        }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        nowPlayingAdapter.removeListener()
        upcomingAdapter.removeListener()
    }

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        setupRecyclerView()
        return binding.root
    }

    private fun setupRecyclerView() {
        val layoutManagerNowPlaying =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        layoutManagerNowPlaying.scrollToPosition(Integer.MAX_VALUE / 2)
        val layoutManagerUpcoming =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        layoutManagerUpcoming.scrollToPosition(Integer.MAX_VALUE / 2)
        with(binding) {
            rvMovies.layoutManager = layoutManagerNowPlaying
            rvMoviesUpcoming.layoutManager = layoutManagerUpcoming
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getLiveData().observe(viewLifecycleOwner, Observer { renderData(it) })
        viewModel.getMoviesFromRemoteSource(TMDB_API_KEY_VALUE, "ru-RU", true)
        viewModel.getMoviesFromRemoteSource(TMDB_API_KEY_VALUE, "ru-RU", false)
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Error -> Toast.makeText(requireActivity(), "Ошибка загрузки", Toast.LENGTH_SHORT).show()
            is AppState.SuccessUpcoming -> {
                with(binding) {
                    movieLoading.visibility = View.GONE
                    rvMoviesUpcoming.adapter = upcomingAdapter
                    upcomingAdapter.setMovies(appState.dataMovies)
                    root.snackBarShow(R.string.success_now, Snackbar.LENGTH_LONG)
                }
            }
            is AppState.SuccessNow -> {
                with(binding) {
                    movieLoading.visibility = View.GONE
                    rvMovies.adapter = nowPlayingAdapter
                    nowPlayingAdapter.setMovies(appState.dataMovies)
                    root.snackBarShow(R.string.success_upcoming, Snackbar.LENGTH_LONG)
                }
            }
            is AppState.Loading -> {
                binding.movieLoading.visibility = View.VISIBLE
            }
            else -> Toast.makeText(requireActivity(), "Ошибка загрузки", Toast.LENGTH_SHORT).show()
        }
    }

    private fun View.snackBarShow(resourceID: Int, duration: Int) {
        Snackbar.make(this, requireActivity().resources.getString(resourceID), duration).show()
    }
}