package search.finder.searchmovies.repository

import search.finder.searchmovies.model.MovieDTO
import search.finder.searchmovies.model.MovieDetailsDTO
import search.finder.searchmovies.room.MovieDao
import search.finder.searchmovies.utils.convertEntityToMovieFavorite
import search.finder.searchmovies.utils.convertEntityToMovieHistory
import search.finder.searchmovies.utils.convertMovieFavoriteToEntity
import search.finder.searchmovies.utils.convertMovieHistoryToEntity

class LocalRepositoryImpl(private val movieDao: MovieDao) : LocalRepository {
    override fun getAllMoviesHistory(): List<MovieDetailsDTO> {
        return convertEntityToMovieHistory(movieDao.getAllHistory())
    }

    override fun saveMovieHistory(movie: MovieDetailsDTO) {
        movieDao.insertMovieHistory(convertMovieHistoryToEntity(movie))
    }

    override fun deleteMoviesHistory() {
        movieDao.deleteAllMoviesHistory()
    }

    override fun getAllFavoriteMovies(): List<MovieDetailsDTO> {
        return convertEntityToMovieFavorite(movieDao.getAllFavoriteMovies())
    }

    override fun saveFavorite(movie: MovieDetailsDTO) {
        movieDao.insertFavorite(convertMovieFavoriteToEntity(movie))
    }

    override fun deleteFavoriteMovie(movie: MovieDetailsDTO) {
        movieDao.deleteFavoriteMovie(convertMovieFavoriteToEntity(movie))
    }
}