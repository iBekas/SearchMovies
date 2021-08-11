package search.finder.searchmovies.view

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import search.finder.searchmovies.R
import search.finder.searchmovies.databinding.MainFragmentBinding
import search.finder.searchmovies.model.MovieDTO
import search.finder.searchmovies.model.NowPlayingDTO
import search.finder.searchmovies.model.UpcomingDTO
import search.finder.searchmovies.view.adapter.NowPlayingAdapter
import search.finder.searchmovies.view.adapter.OnItemViewClickListener
import search.finder.searchmovies.view.adapter.UpcomingAdapter
import search.finder.searchmovies.viewmodel.MainViewModel
import search.finder.searchmovies.viewmodel.MovieLoader

class MainFragment : Fragment() {

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
        //_binding = null
        nowPlayingAdapter.removeListener()
        upcomingAdapter.removeListener()
    }

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context?.let{ LocalBroadcastManager.getInstance(it).registerReceiver(loadResultsReceiver, IntentFilter(MAIN_INTENT_FILTER))}
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        setupRecyclerView()
        setMovieLanguage()
        return binding.root
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

    private fun setMovieLanguage(){
        with(binding){
            mainView.visibility = View.GONE
            movieLoading.visibility = View.VISIBLE
        }
        context?.startService(Intent(context, MainService::class.java).apply {
            putExtra(LANGUAGE_EXTRA, "ru-RU")
        })
    }

    private val loadResultsReceiver: BroadcastReceiver = object : BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            intent?.let{
                when(it.getStringExtra(MAIN_LOAD_RESULT_EXTRA)){
                    MAIN_RESPONSE_SUCCESS_NOW_EXTRA->
                        it.getParcelableArrayListExtra<MovieDTO>(MAIN_MOVIE_NOW_LIST_EXTRA)?.let { it1 ->
                            renderData(true, it1)
                        }
                    MAIN_RESPONSE_SUCCESS_UPCOMING_EXTRA->
                        it.getParcelableArrayListExtra<MovieDTO>(MAIN_MOVIE_NOW_LIST_EXTRA)?.let { it1 ->
                            renderData(false, it1)
                        }
                    else -> null
                }
            }
        }
    }

    private fun renderData(isNow: Boolean, movies: ArrayList<MovieDTO>){
        with(binding) {
            mainView.visibility = View.VISIBLE
            movieLoading.visibility = View.GONE
            if (isNow) {
                    rvMovies.adapter = nowPlayingAdapter
                    nowPlayingAdapter.setMovies(movies)
            } else {
                    rvMoviesUpcoming.adapter = upcomingAdapter
                    upcomingAdapter.setMovies(movies)
            }
            root.snackBarShow(R.string.success, Snackbar.LENGTH_LONG)
        }
    }

    private fun View.snackBarShow(resourceID: Int, duration: Int) {
        Snackbar.make(this, requireActivity().resources.getString(resourceID), duration).show()
    }
}