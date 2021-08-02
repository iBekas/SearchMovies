package search.finder.searchmovies.view

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import search.finder.searchmovies.R
import search.finder.searchmovies.databinding.MainFragmentBinding
import search.finder.searchmovies.model.Movie
import search.finder.searchmovies.view.adapter.NowPlayingAdapter
import search.finder.searchmovies.view.adapter.OnItemViewClickListener
import search.finder.searchmovies.view.adapter.UpcomingAdapter
import search.finder.searchmovies.viewmodel.AppState
import search.finder.searchmovies.viewmodel.MainViewModel

class MainFragment : Fragment() {

    private val nowPlayingAdapter: NowPlayingAdapter = NowPlayingAdapter(object: OnItemViewClickListener{
        override fun onItemViewClick(movie: Movie) {
            val manager = activity?.supportFragmentManager
            if(manager != null){
                val bundle = Bundle()
                bundle.putParcelable(MovieDetailFragment.KEY_MOVIE, movie)
                manager.beginTransaction()
                    .add(R.id.fragment_container, MovieDetailFragment.newInstance(bundle))
                    .addToBackStack("")
                    .commitAllowingStateLoss()
            }
        }
    })

    private val upcomingAdapter: UpcomingAdapter = UpcomingAdapter(object:
        OnItemViewClickListener {
        override fun onItemViewClick(movie: Movie) {
            val manager = activity?.supportFragmentManager
            if(manager != null){
                val bundle = Bundle()
                bundle.putParcelable(MovieDetailFragment.KEY_MOVIE, movie)
                manager.beginTransaction()
                    .add(R.id.fragment_container, MovieDetailFragment.newInstance(bundle))
                    .addToBackStack("")
                    .commitAllowingStateLoss()
            }
        }
    })


    private lateinit var navigation: Navigation
    private lateinit var viewModel: MainViewModel

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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        setupRecyclerView()
        viewModel.getLiveData().observe(viewLifecycleOwner, Observer { renderData(it) })
        viewModel.getMovieNow()
        viewModel.getMovieUpcoming()
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Error -> TODO()
            is AppState.SuccessOld -> {
                binding.movieLoading.visibility = View.GONE
                binding.rvMoviesUpcoming.adapter = upcomingAdapter
                upcomingAdapter.setMovies(appState.dataMovies)
            }
            is AppState.SuccessNew -> {
                binding.movieLoading.visibility = View.GONE
                binding.rvMovies.adapter = nowPlayingAdapter
                nowPlayingAdapter.setMovies(appState.dataMovies)
            }
            is AppState.Loading -> {
                binding.movieLoading.visibility = View.VISIBLE
            }
        }
    }


    private fun setupRecyclerView() {
        val layoutManagerNowPlaying = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvMovies.layoutManager = layoutManagerNowPlaying
        val layoutManagerUpcoming = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvMoviesUpcoming.layoutManager = layoutManagerUpcoming
    }

}