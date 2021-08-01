package search.finder.searchmovies.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import search.finder.searchmovies.databinding.UpcomingFragmentBinding
import search.finder.searchmovies.model.Movie
import search.finder.searchmovies.view.adapter.OnItemViewClickListener
import search.finder.searchmovies.view.adapter.UpcomingFragmentAdapter
import search.finder.searchmovies.viewmodel.AppState
import search.finder.searchmovies.viewmodel.UpcomingViewModel

class UpcomingFragment : Fragment() {

    private val upcomingFragmentAdapter: UpcomingFragmentAdapter = UpcomingFragmentAdapter(object:
        OnItemViewClickListener {
        override fun onItemViewClick(movie: Movie) {
//            val manager = activity?.supportFragmentManager
//            if(manager != null){
//                val bundle = Bundle()
//                bundle.putParcelable(MovieDetailFragment.KEY_MOVIE, movie)
//                manager.beginTransaction()
//                    .add(R.id.now_playing_container, MovieDetailFragment.newInstance(bundle))
//                    .addToBackStack("")
//                    .commitAllowingStateLoss()
//            }
            val bundle = Bundle()
            bundle.putParcelable(MovieDetailFragment.KEY_MOVIE, movie)
            val movieDetailFragment = MovieDetailFragment.newInstance(bundle)
            movieDetailFragment.show(requireActivity().supportFragmentManager, "Detail")
        }
    })

    private lateinit var navigation: Navigation
    private lateinit var viewModel: UpcomingViewModel

    private var _binding: UpcomingFragmentBinding? = null
    private val binding: UpcomingFragmentBinding
    get(): UpcomingFragmentBinding{
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
        upcomingFragmentAdapter.removeListener()
    }

    companion object {
        fun newInstance() = UpcomingFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = UpcomingFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(UpcomingViewModel::class.java)
        viewModel.getLiveData().observe(viewLifecycleOwner, Observer { renderData(it) })
        setupRecyclerView()
        viewModel.getMovieUpcoming()
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Error -> TODO()
            is AppState.Success -> {
                binding.upcomingPlayingLoading.visibility = View.GONE
                binding.rvMoviesUpcoming.adapter = upcomingFragmentAdapter
                upcomingFragmentAdapter.setMovies(appState.dataMovies)
            }
            is AppState.Loading -> {
                binding.upcomingPlayingLoading.visibility = View.VISIBLE
            }
        }
    }

    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvMoviesUpcoming.layoutManager = layoutManager
    }

}