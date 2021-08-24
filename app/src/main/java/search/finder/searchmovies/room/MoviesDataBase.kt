package search.finder.searchmovies.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MovieEntity::class, FavoriteMovieEntity::class, NowPlayingEntity::class], version = 1)
abstract class MoviesDataBase: RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun favoriteMovieDao(): FavoriteMovieDao
    abstract fun nowPlayingDao(): NowPlayingDao
}