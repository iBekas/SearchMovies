package search.finder.searchmovies.repository

import search.finder.searchmovies.model.MovieDTO
import search.finder.searchmovies.room.MovieDao
import search.finder.searchmovies.utils.convertEntityToMovieFavorite
import search.finder.searchmovies.utils.convertEntityToMovieHistory
import search.finder.searchmovies.utils.convertMovieFavoriteToEntity
import search.finder.searchmovies.utils.convertMovieHistoryToEntity

class LocalRepositoryImpl(private val movieDao: MovieDao) : LocalRepository {
    override fun getAllMoviesHistory(): List<MovieDTO> {
        return convertEntityToMovieHistory(movieDao.getAllHistory())
    }

    override fun saveMovieHistory(movie: MovieDTO) {
        movieDao.insertMovieHistory(convertMovieHistoryToEntity(movie))
    }

    override fun deleteMoviesHistory() {
        movieDao.deleteAllMoviesHistory()
    }

    override fun getAllFavoriteMovies(): List<MovieDTO> {
        return convertEntityToMovieFavorite(movieDao.getAllFavoriteMovies())
    }

    override fun saveFavorite(movie: MovieDTO) {
        movieDao.insertFavorite(convertMovieFavoriteToEntity(movie))
    }

    override fun deleteFavoriteMovie(movie: MovieDTO) {
        movieDao.deleteFavoriteMovie(convertMovieFavoriteToEntity(movie))
    }
}