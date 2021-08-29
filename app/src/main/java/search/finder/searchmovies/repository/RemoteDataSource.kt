package search.finder.searchmovies.repository

import com.google.gson.GsonBuilder
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import search.finder.searchmovies.model.*

class RemoteDataSource {
    private val moviesAPI = Retrofit.Builder()
        .baseUrl(TMDB_API_URL)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .build().create(MoviesAPI::class.java)

    fun getMoviesNow(api: String, language: String, callback: Callback<NowPlayingDTO>) {
        moviesAPI.getMoviesNow(TMDB_API_KEY_VALUE, api, language).enqueue(callback)
    }

    fun getMoviesUpcoming(api: String, language: String, callback: Callback<UpcomingDTO>) {
        moviesAPI.getMoviesUpcoming(TMDB_API_KEY_VALUE, api, language).enqueue(callback)
    }

    fun getMovieDetails(id: Long, api: String, language: String, callback: Callback<MovieDetailsDTO>) {
        moviesAPI.getMovieDetails(TMDB_API_KEY_VALUE, id, api, language).enqueue(callback)
    }

    fun getMovieCredits(id: Long, api: String, language: String, callback: Callback<MovieCreditsDTO>) {
        moviesAPI.getMovieCredits(TMDB_API_KEY_VALUE, id, api, language).enqueue(callback)
    }

    fun getPerson(id: Long, api: String, language: String, callback: Callback<PersonDTO>) {
        moviesAPI.getPerson(TMDB_API_KEY_VALUE, id, api, language).enqueue(callback)
    }

}