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

    @Query("SELECT * FROM table_movies WHERE title LIKE '%' || :movieTitle || '%'")
    fun showHistoryMovieByTitle(movieTitle: String): List<FavoriteMovieEntity>
}

@Dao
interface FavoriteMovieDao {

    @Query("SELECT * FROM table_favorite_movies")
    fun getAllFavoriteMovies(): List<FavoriteMovieEntity>

    @Query("SELECT * FROM table_favorite_movies WHERE title LIKE '%' || :movieTitle || '%'")
    fun showFavoriteMovieByTitle(movieTitle: String): List<FavoriteMovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavorite(movie: FavoriteMovieEntity)

    @Delete
    fun deleteFavoriteMovie(movie: FavoriteMovieEntity)
}

@Dao
interface MainMovieDao {

    @Query("SELECT * FROM table_main_movies")
    fun getAllMainMovies(): List<MainMovieEntity>

    @Query("SELECT * FROM table_favorite_movies WHERE title LIKE '%' || :movieTitle || '%'")
    fun showMainMovieByTitle(movieTitle: String): List<MainMovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMainMovies(movies: List<MainMovieEntity>)

//    @Query("INSERT INTO table_main_movies")
//    fun insertMainMovies(movies: List<MainMovieEntity>)

    @Query("DELETE FROM table_main_movies")
    fun deleteMainMovies()
}