package search.finder.searchmovies.repository

import retrofit2.Callback
import search.finder.searchmovies.model.NowPlayingDTO
import search.finder.searchmovies.model.UpcomingDTO

interface Repository {
    fun getMovieFromServerNow(api: String, language: String, callback: Callback<NowPlayingDTO>)
    fun getMovieFromServerUpcoming(api: String, language: String, callback: Callback<UpcomingDTO>)
}