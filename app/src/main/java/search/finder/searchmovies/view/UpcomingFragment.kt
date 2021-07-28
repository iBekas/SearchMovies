package search.finder.searchmovies.view

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import search.finder.searchmovies.R
import search.finder.searchmovies.databinding.NowPlayingFragmentBinding
import search.finder.searchmovies.databinding.UpcomingFragmentBinding
import search.finder.searchmovies.viewmodel.UpcomingViewModel

class UpcomingFragment : Fragment() {

    private lateinit var navigation: Navigation
    private lateinit var binding: UpcomingFragmentBinding
    private lateinit var viewModel: UpcomingViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val activity: MainActivity = context as MainActivity
        navigation = activity.navigation
    }

    companion object {
        fun newInstance() = UpcomingFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = UpcomingFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(UpcomingViewModel::class.java)
        // TODO: Use the ViewModel
    }

}