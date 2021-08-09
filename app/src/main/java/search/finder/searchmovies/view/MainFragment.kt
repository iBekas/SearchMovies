package search.finder.searchmovies.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import search.finder.searchmovies.R
import search.finder.searchmovies.databinding.MainFragmentBinding
import search.finder.searchmovies.model.MovieDTO
import search.finder.searchmovies.view.adapter.NowPlayingAdapter
import search.finder.searchmovies.view.adapter.OnItemViewClickListener
import search.finder.searchmovies.view.adapter.UpcomingAdapter
import search.finder.searchmovies.viewmodel.AppState
import search.finder.searchmovies.viewmodel.MainViewModel
import search.finder.searchmovies.viewmodel.MovieLoader

class MainFragment : Fragment(){

    private val nowPlayingAdapter: NowPlayingAdapter =
        NowPlayingAdapter(object : OnItemViewClickListener {
            override fun onItemViewClick(movie: MovieDTO) {
                activity?.supportFragmentManager?.apply {
                    beginTransaction()
                        .replace(
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

    private val upcomingAdapter: UpcomingAdapter = UpcomingAdapter(object :
        OnItemViewClickListener {
        override fun onItemViewClick(movie: MovieDTO) {
            activity?.supportFragmentManager?.apply {
                beginTransaction()
                    .replace(
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


    private lateinit var navigation: Navigation
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    private var _binding: MainFragmentBinding? = null
    private val binding: MainFragmentBinding
        get(): MainFragmentBinding {
            return _binding!!
        }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val activity: MainActivity = context as MainActivity
        navigation = activity.navigation
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
        MovieLoader().loadNowPlaying()
        MovieLoader().loadUpcoming()
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupRecyclerView()
        with(viewModel) {
            getLiveData().observe(viewLifecycleOwner, { renderData(it) })
            getMovieNow()
            getMovieUpcoming()
        }
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Error -> TODO()
            is AppState.SuccessOld -> {
                with(binding) {
                    movieLoading.visibility = View.GONE
                    rvMoviesUpcoming.adapter = upcomingAdapter
                    upcomingAdapter.setMovies(appState.dataMovies)
                    root.snackBarShow(R.string.success, Snackbar.LENGTH_LONG)
                }
            }
            is AppState.SuccessNew -> {
                with(binding) {
                    movieLoading.visibility = View.GONE
                    rvMovies.adapter = nowPlayingAdapter
                    nowPlayingAdapter.setMovies(appState.dataMovies)
                    /*root.snackBarShow(R.string.success_upcoming, Snackbar.LENGTH_LONG)*/
                }
            }
            is AppState.Loading -> {
                binding.movieLoading.visibility = View.VISIBLE
            }
        }
    }


    private fun setupRecyclerView() {
        val layoutManagerNowPlaying =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        val layoutManagerUpcoming =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        with(binding) {
            rvMovies.layoutManager = layoutManagerNowPlaying
            rvMoviesUpcoming.layoutManager = layoutManagerUpcoming
        }
    }

    private fun View.snackBarShow(resourceID: Int, duration: Int) {
        Snackbar.make(this, requireActivity().resources.getString(resourceID), duration).show()
    }

    /* Попытка не пытка, без переменной не расширяется ¯\_(ツ)_/¯
    fun Snackbar.snackBar(snackbar: Snackbar, view: View, resourceID: Int, duration: Int) {
        Snackbar.make(view, requireActivity().resources.getString(resourceID), duration).show()
    }*/

}