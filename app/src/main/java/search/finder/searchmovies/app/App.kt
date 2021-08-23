package search.finder.searchmovies.app

import android.app.Application
import androidx.room.Room
import search.finder.searchmovies.room.FavoriteMovieDao
import search.finder.searchmovies.room.MainMovieDao
import search.finder.searchmovies.room.MovieDao
import search.finder.searchmovies.room.MoviesDataBase

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
            if(db ==null){
                val builder = Room.databaseBuilder(
                    appInstance!!.applicationContext,
                    MoviesDataBase::class.java,
                    nameDB
                )
                db = builder
                    .allowMainThreadQueries()
                    .build()
            }
            return db!!.movieDao()
        }

        fun getFavoriteMovieDao():FavoriteMovieDao{
            if(db ==null){
                val builder = Room.databaseBuilder(
                    appInstance!!.applicationContext,
                    MoviesDataBase::class.java,
                    nameDB
                )
                db = builder
                    .allowMainThreadQueries()
                    .build()
            }
            return db!!.favoriteMovieDao()
        }

        fun getMainMovieDao():MainMovieDao{
            if(db ==null){
                val builder = Room.databaseBuilder(
                    appInstance!!.applicationContext,
                    MoviesDataBase::class.java,
                    nameDB
                )
                db = builder
                    .allowMainThreadQueries()
                    .build()
            }
            return db!!.mainMovieDao()
        }


    }
}