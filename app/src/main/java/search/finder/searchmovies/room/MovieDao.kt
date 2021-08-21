package search.finder.searchmovies.room

import androidx.room.*

@Dao
interface MovieDao {

    @Query("SELECT * FROM table_movies")
    fun getAllHistory(): List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovieHistory(movie: MovieEntity)

    @Query("DELETE FROM table_movies")
    fun deleteAllMoviesHistory()
}

@Dao
interface FavoriteMovieDao {

    @Query("SELECT * FROM table_favorite_movies")
    fun getAllFavoriteMovies(): List<FavoriteMovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavorite(movie: FavoriteMovieEntity)

    @Delete
    fun deleteFavoriteMovie(movie: FavoriteMovieEntity)
}