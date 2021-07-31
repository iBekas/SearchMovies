package search.finder.searchmovies.view

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import search.finder.searchmovies.R
import search.finder.searchmovies.databinding.NowPlayingFragmentBinding
import search.finder.searchmovies.databinding.UpcomingFragmentBinding
import search.finder.searchmovies.viewmodel.NowPlayingViewModel

class NowPlayingFragment : Fragment() {

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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = NowPlayingFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(NowPlayingViewModel::class.java)
        // TODO: Use the ViewModel
    }
}