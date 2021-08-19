package search.finder.searchmovies.repository

import search.finder.searchmovies.model.MovieDTO

interface LocalRepository {
    fun getAllMoviesHistory(): List<MovieDTO>
    fun saveMovieHistory(movie:MovieDTO)
    fun deleteMoviesHistory()

    fun getAllFavoriteMovies(): List<MovieDTO>
    fun saveFavorite(movie:MovieDTO)
    fun deleteFavoriteMovie(movie:MovieDTO)
}