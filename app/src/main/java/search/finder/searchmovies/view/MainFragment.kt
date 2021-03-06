package search.finder.searchmovies.view

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import search.finder.searchmovies.BuildConfig
import search.finder.searchmovies.R
import search.finder.searchmovies.databinding.MainFragmentBinding
import search.finder.searchmovies.model.MovieDTO
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
                callDetailFragment(movie)
                val movieTitleToSend = movie.title
                val intent = Intent()
                intent.action = ACTION_SEND_MOVIE_TITLE
                intent.putExtra(SEND_MOVIE_TITLE, movieTitleToSend)
                intent.addFlags(Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP)
                context?.sendBroadcast(intent)
            }
        })

    private val upcomingAdapter: UpcomingAdapter = UpcomingAdapter(object :
        OnItemViewClickListener {
        override fun onItemViewClick(movie: MovieDTO) {
            callDetailFragment(movie)
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
        viewModel.deleteNowPlayingMovies()
        viewModel.deleteUpcomingMovies()
    }

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
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
        viewModel.getLiveData().observe(viewLifecycleOwner, { renderData(it) })
        viewModel.getMoviesFromRemoteSource(BuildConfig.TMDB_API_KEY_VALUE, "ru-RU", true)
        viewModel.getMoviesFromRemoteSource(BuildConfig.TMDB_API_KEY_VALUE, "ru-RU", false)
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Error -> Toast.makeText(
                requireActivity(),
                "???????????? ????????????????",
                Toast.LENGTH_SHORT
            ).show()
            is AppState.SuccessUpcoming -> {
                with(binding) {
                    movieLoading.visibility = View.GONE
                    rvMoviesUpcoming.adapter = upcomingAdapter
                    upcomingAdapter.setMovies(appState.dataMovies)
                    viewModel.saveUpcomingMoviesToDb(appState.dataMovies)
                    root.snackBarShow(R.string.success_now, Snackbar.LENGTH_LONG)
                }
            }
            is AppState.SuccessNow -> {
                with(binding) {
                    movieLoading.visibility = View.GONE
                    rvMovies.adapter = nowPlayingAdapter
                    nowPlayingAdapter.setMovies(appState.dataMovies)
                    viewModel.saveNowPlayingMoviesToDb(appState.dataMovies)
                    root.snackBarShow(R.string.success_upcoming, Snackbar.LENGTH_LONG)
                }
            }
            is AppState.Loading -> {
                binding.movieLoading.visibility = View.VISIBLE
            }
            else -> Toast.makeText(requireActivity(), "???????????? ????????????????", Toast.LENGTH_SHORT).show()
        }
    }

    private fun View.snackBarShow(resourceID: Int, duration: Int) {
        Snackbar.make(this, requireActivity().resources.getString(resourceID), duration).show()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search, menu)
        val search = menu.findItem(R.id.search)
        val searchView: SearchView = search.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.rvMovies.adapter = nowPlayingAdapter
                nowPlayingAdapter.setMovies(viewModel.showNowPlayingMovieByTitle(query ?: " "))
                binding.rvMoviesUpcoming.adapter = upcomingAdapter
                upcomingAdapter.setMovies(viewModel.showUpcomingMovieByTitle(query ?: " "))
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
        searchView.setOnCloseListener {
            viewModel.getNowPlayingMoviesFromDb()
            viewModel.getUpcomingMoviesFromDb()
            false
        }
    }

    private fun callDetailFragment(movie: MovieDTO) {
        activity?.supportFragmentManager?.apply {
            beginTransaction()
                .setCustomAnimations(
                    R.anim.enter_fragment,
                    R.anim.exit_fragment,
                    R.anim.enter_fragment_in,
                    R.anim.exit_fragment_out
                )
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
}