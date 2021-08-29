package search.finder.searchmovies.repository

import retrofit2.Callback
import search.finder.searchmovies.model.MovieCreditsDTO
import search.finder.searchmovies.model.MovieDetailsDTO
import search.finder.searchmovies.model.PersonDTO

class DetailRepositoryImpl(private val remoteDataSource: RemoteDataSource): DetailRepository {
    override fun getMovieDetailsFromServer(
        id: Long,
        api: String,
        language: String,
        callback: Callback<MovieDetailsDTO>
    ) {
        remoteDataSource.getMovieDetails(id, api, language, callback)
    }

    override fun getMovieCreditsFromServer(
        id: Long,
        api: String,
        language: String,
        callback: Callback<MovieCreditsDTO>
    ) {
        remoteDataSource.getMovieCredits(id, api, language, callback)
    }

    override fun getPersonFromServer(
        id: Long,
        api: String,
        language: String,
        callback: Callback<PersonDTO>
    ) {
        remoteDataSource.getPerson(id, api, language, callback)
    }
}