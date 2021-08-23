package search.finder.searchmovies.repository

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import search.finder.searchmovies.model.MovieDetailsDTO
import search.finder.searchmovies.room.FavoriteMovieEntity
import search.finder.searchmovies.room.MainMovieEntity

interface LocalRepository {
    fun getAllMoviesHistory(): List<MovieDetailsDTO>
    fun saveMovieHistory(movie:MovieDetailsDTO)
    fun deleteMoviesHistory()
    fun showHistoryMovieByTitle(movieTitle: String): List<MovieDetailsDTO>

    fun getAllFavoriteMovies(): List<MovieDetailsDTO>
    fun showFavoriteMovieByTitle(movieTitle: String): List<MovieDetailsDTO>
    fun saveFavorite(movie:MovieDetailsDTO)
    fun deleteFavoriteMovie(movie:MovieDetailsDTO)

    fun getAllMainMovies(): List<MovieDetailsDTO>
    fun showMainMovieByTitle(movieTitle: String): List<MovieDetailsDTO>
    fun saveMainMovies(movies: List<MovieDetailsDTO>)
    fun deleteMainMovies()
}