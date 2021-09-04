package search.finder.searchmovies.view.map

import android.location.Geocoder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import search.finder.searchmovies.BuildConfig
import search.finder.searchmovies.R
import search.finder.searchmovies.databinding.FragmentMainMapsBinding
import search.finder.searchmovies.model.MovieCreditsDTO
import search.finder.searchmovies.model.PersonDTO
import search.finder.searchmovies.viewmodel.AppState
import search.finder.searchmovies.viewmodel.DetailViewModel

class MapsFragment : Fragment() {

    private var _binding: FragmentMainMapsBinding? = null
    private val binding: FragmentMainMapsBinding
        get() :FragmentMainMapsBinding {
            return _binding!!
        }

    private val viewModel: DetailViewModel by lazy {
        ViewModelProvider(this).get(DetailViewModel::class.java)
    }

    companion object {
        const val KEY_MOVIE_DETAILS = "KEY"
        fun newInstance(bundle: Bundle): MapsFragment {
            val fragment = MapsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    private val markers: ArrayList<Marker> = arrayListOf()
    private lateinit var map: GoogleMap
    private val callback = OnMapReadyCallback { googleMap ->
        map = googleMap
        googleMap.uiSettings.isZoomControlsEnabled = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainMapsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        viewModel.getLiveData().observe(viewLifecycleOwner, { renderData(it) })
        val movie = arguments?.getParcelable<MovieCreditsDTO>(KEY_MOVIE_DETAILS)
        movie?.let{ viewModel.getPersonFromRemoteSource(movie.cast[0].id, BuildConfig.TMDB_API_KEY_VALUE, "ru-RU")}
        mapFragment?.getMapAsync(callback)
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Loading -> binding.mapLoading.visibility = View.VISIBLE
            is AppState.Error -> Toast.makeText(
                requireActivity(),
                "Ошибка загрузки",
                Toast.LENGTH_SHORT
            ).show()
            is AppState.SuccessPerson -> {
                setData(appState.dataMovie)
                binding.mapLoading.visibility = View.GONE
            }
            else -> Toast.makeText(requireActivity(), "Ошибка загрузки", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setData(person: PersonDTO) {
        with(binding) {
            placeOfBirth.text = String.format("%s %s", requireActivity().resources.getString(R.string.Map_title), person.name)
            val geoCoder = Geocoder(context)
            val address = geoCoder.getFromLocationName(person.place_of_birth?: "Pacific Ocean", 1)
            /*В строке выше, студия просит убрать оператор Элвис, но, в случаях null в месте рождения актера, приложение без :? упадет*/
            val location = LatLng(address[0].latitude, address[0].longitude)
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 5f))
            addMarker(location)
        }
    }

    private fun addMarker(location: LatLng) {
        val marker = setMarker(location, markers.size.toString(), R.drawable.ic_map_pin)
        markers.add(marker)
    }

    private fun setMarker(location: LatLng, searchText: String, resourceId: Int): Marker {
        return map.addMarker(
            MarkerOptions()
                .position(location)
                .title(searchText)
                .icon(BitmapDescriptorFactory.fromResource(resourceId))
        )
    }


}