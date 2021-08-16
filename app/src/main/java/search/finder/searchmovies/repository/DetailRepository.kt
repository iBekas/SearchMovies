package search.finder.searchmovies.repository

import retrofit2.Callback
import search.finder.searchmovies.model.MovieDetailsDTO

interface DetailRepository {
    fun getMovieDetailsFromServer(id: Long, api: String, language: String, callback: Callback<MovieDetailsDTO>)
}