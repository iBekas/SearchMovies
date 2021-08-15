package search.finder.searchmovies.repository

import retrofit2.Callback
import search.finder.searchmovies.model.*

class RepositoryImpl(private val remoteDataSource: RemoteDataSource): Repository {
    override fun getMovieFromServerNow(api: String, language: String, callback: Callback<NowPlayingDTO>) = remoteDataSource.getMoviesNow(api, language, callback)

    override fun getMovieFromServerUpcoming(api: String, language: String, callback: Callback<UpcomingDTO>) =remoteDataSource.getMoviesUpcoming(api, language, callback)

    override fun getMovieFromLocalNow() = getMoviesNow()
    override fun getMovieFromLocalUpcoming() = getMoviesUpcoming()
}