package search.finder.searchmovies.repository

import search.finder.searchmovies.model.MovieDetailsDTO

interface LocalRepository {
    fun getAllMoviesHistory(): List<MovieDetailsDTO>
    fun saveMovieHistory(movie:MovieDetailsDTO)
    fun deleteMoviesHistory()

    fun getAllFavoriteMovies(): List<MovieDetailsDTO>
    fun saveFavorite(movie:MovieDetailsDTO)
    fun deleteFavoriteMovie(movie:MovieDetailsDTO)
}