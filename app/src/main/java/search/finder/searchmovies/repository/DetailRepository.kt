package search.finder.searchmovies.repository

import retrofit2.Callback
import search.finder.searchmovies.model.MovieCreditsDTO
import search.finder.searchmovies.model.MovieDetailsDTO
import search.finder.searchmovies.model.PersonDTO

interface DetailRepository {
    fun getMovieDetailsFromServer(id: Long, api: String, language: String, callback: Callback<MovieDetailsDTO>)
    fun getMovieCreditsFromServer(id: Long, api: String, language: String, callback: Callback<MovieCreditsDTO>)
    fun getPersonFromServer(id: Long, api: String, language: String, callback: Callback<PersonDTO>)
}