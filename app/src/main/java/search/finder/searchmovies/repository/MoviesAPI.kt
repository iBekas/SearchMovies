package search.finder.searchmovies.repository

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query
import search.finder.searchmovies.model.*

interface MoviesAPI {
    @GET(TMDB_API_URL_END_POINTER_NOW_PLAYING)
    fun getMoviesNow(
        @Header(TMDB_API_KEY_NAME) token:String,
        @Query("api_key") api: String,
        @Query("language") language: String,
    ): Call<NowPlayingDTO>

    @GET(TMDB_API_URL_END_POINTER_UPCOMING)
    fun getMoviesUpcoming(
        @Header(TMDB_API_KEY_NAME) token:String,
        @Query("api_key") api: String,
        @Query("language") language: String,
    ): Call<UpcomingDTO>

    @GET (TMDB_API_URL_DETAILS)
    fun getMovieDetails(
        @Header(TMDB_API_KEY_NAME) token:String,
        @Path("movie_id") movie_id: Long,
        @Query("api_key") api: String,
        @Query("language") language: String
    ): Call<MovieDetailsDTO>
}
