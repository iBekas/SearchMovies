package search.finder.searchmovies.view.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import search.finder.searchmovies.R
import search.finder.searchmovies.databinding.FragmentMainMapsBinding
import search.finder.searchmovies.model.MovieDetailsDTO
import search.finder.searchmovies.viewmodel.DetailViewModel

class MapsFragment : Fragment() {

    private var _binding: FragmentMainMapsBinding? = null
    private val binding: FragmentMainMapsBinding
        get() :FragmentMainMapsBinding { return _binding!! }

    private val viewModel: DetailViewModel by lazy {
        ViewModelProvider(this).get(DetailViewModel::class.java)
    }

    companion object {
        const val KEY_MOVIE_DETAILS = "KEY"
        fun newInstance(bundle: Bundle): MapsFragment{
            val fragment = MapsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    private val callback = OnMapReadyCallback { googleMap ->
        val sydney = LatLng(-34.0, 151.0)
        googleMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentMainMapsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        val movie = arguments?.getParcelable<MovieDetailsDTO>(KEY_MOVIE_DETAILS)
        mapFragment?.getMapAsync(callback)
    }
}