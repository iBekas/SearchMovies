package search.finder.searchmovies.view

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import search.finder.searchmovies.R
import search.finder.searchmovies.databinding.NowPlayingFragmentBinding
import search.finder.searchmovies.model.Movie
import search.finder.searchmovies.view.adapter.NowPlayingFragmentAdapter
import search.finder.searchmovies.view.adapter.OnItemViewClickListener
import search.finder.searchmovies.viewmodel.AppState
import search.finder.searchmovies.viewmodel.NowPlayingViewModel

class NowPlayingFragment : Fragment() {

    private val nowPlayingFragmentAdapter: NowPlayingFragmentAdapter = NowPlayingFragmentAdapter(object: OnItemViewClickListener{
        override fun onItemViewClick(movie: Movie) {
            val manager = activity?.supportFragmentManager
            if(manager != null){
                val bundle = Bundle()
                bundle.putParcelable(MovieDetailFragment.KEY_MOVIE, movie)
                manager.beginTransaction()
                    .add(R.id.now_playing_container, MovieDetailFragment.newInstance(bundle))
                    .addToBackStack("")
                    .commitAllowingStateLoss()
            }
        }
    })
    private lateinit var navigation: Navigation
    private lateinit var viewModel: NowPlayingViewModel

    private var _binding: NowPlayingFragmentBinding? = null
    private val binding: NowPlayingFragmentBinding
        get(): NowPlayingFragmentBinding {
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
    }

    companion object {
        fun newInstance() = NowPlayingFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = NowPlayingFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(NowPlayingViewModel::class.java)
        viewModel.getLiveData().observe(viewLifecycleOwner, Observer { renderData(it) })
        setupRecyclerView()
        viewModel.getMovieNow()
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Error -> TODO()
            is AppState.Success -> {
                binding.nowPlayingLoading.visibility = View.GONE
                binding.rvMovies.adapter = nowPlayingFragmentAdapter
                nowPlayingFragmentAdapter.setMovies(appState.dataMovies)
            }
            is AppState.Loading -> {
                binding.nowPlayingLoading.visibility = View.GONE
            }
        }
    }

    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvMovies.layoutManager = layoutManager
    }

}