package search.finder.searchmovies.view

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import search.finder.searchmovies.R
import search.finder.searchmovies.databinding.ContentMainBinding
import search.finder.searchmovies.view.contentprovider.ContactsFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ContentMainBinding
    private val receiver = MainReceiver()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ContentMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        savedInstanceState.let {
            supportFragmentManager.beginTransaction()
                .setCustomAnimations(R.anim.enter_fragment, R.anim.exit_fragment)
                .replace(R.id.fragment_container, MainFragment.newInstance()).commit()
        }
        initToolbar()
        registerReceiver(receiver, IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED))
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }


    private fun initToolbar(): Toolbar {
        setSupportActionBar(binding.toolbar)
        /*supportActionBar!!.setDisplayHomeAsUpEnabled(true)*/
        return binding.toolbar
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.context_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.contacts -> {
                supportFragmentManager.beginTransaction()
                    .setCustomAnimations(
                        R.anim.enter_fragment,
                        R.anim.exit_fragment,
                        R.anim.enter_fragment_in,
                        R.anim.exit_fragment_out
                    )
                    .add(R.id.fragment_container, ContactsFragment.newInstance()).addToBackStack("")
                    .commit()
                return true
            }
            R.id.settings -> {
                makeText(this, "Нстраиваем", Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.favorite -> {
                supportFragmentManager.beginTransaction()
                    .setCustomAnimations(
                        R.anim.enter_fragment,
                        R.anim.exit_fragment,
                        R.anim.enter_fragment_in,
                        R.anim.exit_fragment_out
                    )
                    .add(R.id.fragment_container, FavoriteFragment.newInstance()).addToBackStack("")
                    .commit()
                return true
            }
            R.id.history -> {
                supportFragmentManager.beginTransaction()
                    .setCustomAnimations(
                        R.anim.enter_fragment,
                        R.anim.exit_fragment,
                        R.anim.enter_fragment_in,
                        R.anim.exit_fragment_out
                    )
                    .add(R.id.fragment_container, HistoryMovieFragment.newInstance())
                    .addToBackStack("").commit()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

}