package search.finder.searchmovies.repository

import retrofit2.Callback
import search.finder.searchmovies.model.MovieDetailsDTO

class DetailRepositoryImpl(private val remoteDataSource: RemoteDataSource): DetailRepository {
    override fun getMovieDetailsFromServer(
        id: Long,
        api: String,
        language: String,
        callback: Callback<MovieDetailsDTO>
    ) {
        remoteDataSource.getMovieDetails(id, api, language, callback)
    }
}