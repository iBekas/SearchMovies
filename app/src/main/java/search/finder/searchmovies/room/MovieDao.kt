package search.finder.searchmovies.room

import androidx.room.*

@Dao
interface MovieDao {

    @Query("SELECT * FROM table_movies")
    fun selectAll(): List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(movie: MovieEntity)

    @Delete
    fun deleteMovie(movie: MovieEntity)

    @Query("SELECT * FROM table_favorite_movies")
    fun getAllFavoriteMovies():List<FavoriteMovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavorite(movie:FavoriteMovieEntity)

    @Delete
    fun deleteMovie(movie: FavoriteMovieEntity)
}