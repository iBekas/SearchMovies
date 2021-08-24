package search.finder.searchmovies.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import search.finder.searchmovies.app.App
import search.finder.searchmovies.model.MovieDetailsDTO
import search.finder.searchmovies.repository.DetailRepositoryImpl
import search.finder.searchmovies.repository.LocalRepositoryImpl
import search.finder.searchmovies.repository.RemoteDataSource

class DetailViewModel(
    private val liveDataObserver: MutableLiveData<AppState> = MutableLiveData(),
    private val repository: DetailRepositoryImpl = DetailRepositoryImpl(RemoteDataSource()),
    private val localRepository: LocalRepositoryImpl = LocalRepositoryImpl(
        App.getMovieDao(),
        App.getFavoriteMovieDao(),
        App.getNowPlayingMovieDao(),
        App.getUpcomingMovieDao()
    )
) : ViewModel() {
    fun getLiveData() = liveDataObserver

    fun saveMovieHistoryToDb(movie: MovieDetailsDTO) = localRepository.saveMovieHistory(movie)

    fun saveFavoriteMovieToDb(movie: MovieDetailsDTO) = localRepository.saveFavorite(movie)
    fun deleteFavoriteMovieFromDb(movie: MovieDetailsDTO) = localRepository.deleteFavoriteMovie(movie)


    fun getMovieFromRemoteSource(id: Long, api: String, language: String) {
        repository.getMovieDetailsFromServer(id, api, language, callBack)
    }

    private val callBack = object : Callback<MovieDetailsDTO> {

        override fun onResponse(call: Call<MovieDetailsDTO>, response: Response<MovieDetailsDTO>) {
            val serverResponse: MovieDetailsDTO? = response.body()
            if (response.isSuccessful && serverResponse != null) {
                liveDataObserver.postValue(AppState.SuccessDetail(serverResponse))
            } else {
                liveDataObserver.postValue(AppState.Error(NullPointerException()))
            }
        }

        override fun onFailure(call: Call<MovieDetailsDTO>, t: Throwable) {
            liveDataObserver.postValue(AppState.Error(NullPointerException())) //TODO что-то адекватное
        }
    }
}