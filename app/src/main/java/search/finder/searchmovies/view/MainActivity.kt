package search.finder.searchmovies.view

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import search.finder.searchmovies.R
import search.finder.searchmovies.databinding.ContentMainBinding
import search.finder.searchmovies.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {

    val navigation = Navigation(supportFragmentManager)
    lateinit var binding: ContentMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ContentMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(R.id.now_playing_container, NowPlayingFragment.newInstance()).commit()
//            navigation.addFragment(NowPlayingFragment.newInstance(), R.id.now_playing_container, true)
            navigation.addFragment(UpcomingFragment.newInstance(), R.id.upcoming_movies_container, true)
        }
        initToolbar()
    }

    private fun initToolbar(): Toolbar {
        setSupportActionBar(binding.toolbar)
//        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        return binding.toolbar
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.context_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.sort -> {
                Toast.makeText(this, "Сортируем", Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.settings -> {
                Toast.makeText(this, "Нстраиваем", Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.favorite -> {
                Toast.makeText(this, "Держи избранное", Toast.LENGTH_SHORT).show()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}