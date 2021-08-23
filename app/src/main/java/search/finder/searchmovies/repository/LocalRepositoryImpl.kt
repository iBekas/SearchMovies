package search.finder.searchmovies.repository

import search.finder.searchmovies.model.MovieDetailsDTO
import search.finder.searchmovies.room.FavoriteMovieDao
import search.finder.searchmovies.room.MovieDao
import search.finder.searchmovies.utils.convertEntityToMovieFavorite
import search.finder.searchmovies.utils.convertEntityToMovieHistory
import search.finder.searchmovies.utils.convertMovieFavoriteToEntity
import search.finder.searchmovies.utils.convertMovieHistoryToEntity

class LocalRepositoryImpl(private val movieDao: MovieDao, private val favoriteMovieDao: FavoriteMovieDao) : LocalRepository {
    override fun getAllMoviesHistory(): List<MovieDetailsDTO> {
        return convertEntityToMovieHistory(movieDao.getAllHistory())
    }

    override fun saveMovieHistory(movie: MovieDetailsDTO) {
        movieDao.insertMovieHistory(convertMovieHistoryToEntity(movie))
    }

    override fun deleteMoviesHistory() {
        movieDao.deleteAllMoviesHistory()
    }

    override fun showHistoryMovieByTitle(movieTitle: String): List<MovieDetailsDTO> {
        return convertEntityToMovieFavorite(movieDao.showHistoryMovieByTitle(movieTitle))
    }

    override fun getAllFavoriteMovies(): List<MovieDetailsDTO> {
        return convertEntityToMovieFavorite(favoriteMovieDao.getAllFavoriteMovies())
    }

    override fun showFavoriteMovieByTitle(movieTitle: String): List<MovieDetailsDTO> {
        return convertEntityToMovieFavorite(favoriteMovieDao.showFavoriteMovieByTitle(movieTitle))
    }

    override fun saveFavorite(movie: MovieDetailsDTO) {
        favoriteMovieDao.insertFavorite(convertMovieFavoriteToEntity(movie))
    }

    override fun deleteFavoriteMovie(movie: MovieDetailsDTO) {
        favoriteMovieDao.deleteFavoriteMovie(convertMovieFavoriteToEntity(movie))
    }
}