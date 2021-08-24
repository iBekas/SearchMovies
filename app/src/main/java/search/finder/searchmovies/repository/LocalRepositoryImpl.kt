package search.finder.searchmovies.repository

import search.finder.searchmovies.model.MovieDTO
import search.finder.searchmovies.model.MovieDetailsDTO
import search.finder.searchmovies.room.*
import search.finder.searchmovies.utils.*

class LocalRepositoryImpl(private val movieDao: MovieDao,
                          private val favoriteMovieDao: FavoriteMovieDao,
                          private val nowPlayingDao: NowPlayingDao,
                          private val upcomingDao: UpcomingDao
                          ) : LocalRepository {
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

    override fun getAllNowPlayingMovies(): List<MovieDTO> {
        return convertEntityToMovieNowPlaying(nowPlayingDao.getAllNowPlayingMovies())
    }
    override fun showNowPlayingMovieByTitle(movieTitle: String): List<MovieDTO> {
        return convertEntityToMovieNowPlaying(nowPlayingDao.showNowPlayingMovieByTitle(movieTitle))
    }
    override fun saveNowPlayingMovies(movies: List<MovieDTO>) {
        nowPlayingDao.insertNowPlayingMovies(convertMovieNowPlayingToEntity(movies))
    }
    override fun deleteNowPlayingMovies() {
        nowPlayingDao.deleteNowPlayingMovies()
    }

    override fun getAllUpcomingMovies(): List<MovieDTO> {
        return convertEntityToMovieUpcoming(upcomingDao.getAllUpcomingMovies())
    }
    override fun showUpcomingMovieByTitle(movieTitle: String): List<MovieDTO> {
        return convertEntityToMovieUpcoming(upcomingDao.showUpcomingMovieByTitle(movieTitle))
    }
    override fun saveUpcomingMovies(movies: List<MovieDTO>) {
        upcomingDao.insertUpcomingMovies(convertMovieUpcomingToEntity(movies))
    }
    override fun deleteUpcomingMovies() {
        upcomingDao.deleteUpcomingMovies()
    }


}