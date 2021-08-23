package search.finder.searchmovies.repository

import search.finder.searchmovies.model.MovieDetailsDTO
import search.finder.searchmovies.room.*
import search.finder.searchmovies.utils.*

class LocalRepositoryImpl(private val movieDao: MovieDao, private val favoriteMovieDao: FavoriteMovieDao, private val mainMovieDao: MainMovieDao) : LocalRepository {
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

    override fun getAllMainMovies(): List<MovieDetailsDTO> {
        return convertEntityToMovieMain(mainMovieDao.getAllMainMovies())
    }

    override fun showMainMovieByTitle(movieTitle: String): List<MovieDetailsDTO> {
        return convertEntityToMovieMain(mainMovieDao.showMainMovieByTitle(movieTitle))
    }

    override fun saveMainMovies(movies: List<MovieDetailsDTO>) {
//        mainMovieDao.insertMainMovies(convertMovieMainToEntity(movies))
    }

    override fun deleteMainMovies() {

    }
}