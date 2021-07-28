package search.finder.searchmovies.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import search.finder.searchmovies.R
import search.finder.searchmovies.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {

    val navigation = Navigation(supportFragmentManager)
    lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(R.id.now_playing_container, NowPlayingFragment.newInstance()).commit()
//            navigation.addFragment(NowPlayingFragment.newInstance(), R.id.now_playing_container, true)
            navigation.addFragment(UpcomingFragment.newInstance(), R.id.upcoming_movies_container, true)
        }
    }
}