package search.finder.searchmovies.repository

import com.google.gson.GsonBuilder
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import search.finder.searchmovies.model.NowPlayingDTO
import search.finder.searchmovies.model.TMDB_API_URL
import search.finder.searchmovies.model.UpcomingDTO

class RemoteDataSource {
    private val moviesAPI = Retrofit.Builder()
        .baseUrl(TMDB_API_URL)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        .build().create(MoviesAPI::class.java)

    fun getMoviesNow(api: String, language: String, callback: Callback<NowPlayingDTO>) {
        moviesAPI.getMoviesNow(api, language).enqueue(callback)
    }

    fun getMoviesUpcoming(api: String, language: String, callback: Callback<UpcomingDTO>) {
        moviesAPI.getMoviesUpcoming(api, language).enqueue(callback)
    }
}