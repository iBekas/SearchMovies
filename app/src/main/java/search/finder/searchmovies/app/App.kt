package search.finder.searchmovies.app

import android.app.Application
import androidx.room.Room
import search.finder.searchmovies.room.*

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }
    companion object{
        private var appInstance : App? = null
        private var db: MoviesDataBase? = null
        private var nameDB = "Movies"

        fun getMovieDao():MovieDao{
            checkDb()
            return db!!.movieDao()
        }

        fun getFavoriteMovieDao():FavoriteMovieDao{
            checkDb()
            return db!!.favoriteMovieDao()
        }

        fun getNowPlayingMovieDao():NowPlayingDao{
            checkDb()
            return db!!.nowPlayingDao()
        }

        fun getUpcomingMovieDao():UpcomingDao{
            checkDb()
            return db!!.upcomingDao()
        }

        private fun checkDb() {
            if (db == null) {
                val builder = Room.databaseBuilder(
                    appInstance!!.applicationContext,
                    MoviesDataBase::class.java,
                    nameDB
                )
                db = builder
                    .allowMainThreadQueries()
                    .build()
            }
        }
    }
}