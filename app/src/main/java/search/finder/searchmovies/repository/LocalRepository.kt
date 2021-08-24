package search.finder.searchmovies.repository

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import search.finder.searchmovies.model.MovieDTO
import search.finder.searchmovies.model.MovieDetailsDTO
import search.finder.searchmovies.room.NowPlayingEntity

interface LocalRepository {
    fun getAllMoviesHistory(): List<MovieDetailsDTO>
    fun saveMovieHistory(movie:MovieDetailsDTO)
    fun deleteMoviesHistory()
    fun showHistoryMovieByTitle(movieTitle: String): List<MovieDetailsDTO>

    fun getAllFavoriteMovies(): List<MovieDetailsDTO>
    fun showFavoriteMovieByTitle(movieTitle: String): List<MovieDetailsDTO>
    fun saveFavorite(movie:MovieDetailsDTO)
    fun deleteFavoriteMovie(movie:MovieDetailsDTO)

    fun getAllNowPlayingMovies(): List<MovieDTO>
    fun showNowPlayingMovieByTitle(movieTitle: String): List<MovieDTO>
    fun saveNowPlayingMovies(movies: List<MovieDTO>)
    fun deleteNowPlayingMovies()

    fun getAllUpcomingMovies(): List<MovieDTO>
    fun showUpcomingMovieByTitle(movieTitle: String): List<MovieDTO>
    fun saveUpcomingMovies(movies: List<MovieDTO>)
    fun deleteUpcomingMovies()
}