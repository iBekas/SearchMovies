package search.finder.searchmovies.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import search.finder.searchmovies.app.App
import search.finder.searchmovies.model.MovieDTO
import search.finder.searchmovies.model.NowPlayingDTO
import search.finder.searchmovies.model.UpcomingDTO
import search.finder.searchmovies.repository.LocalRepositoryImpl
import search.finder.searchmovies.repository.RemoteDataSource
import search.finder.searchmovies.repository.Repository
import search.finder.searchmovies.repository.RepositoryImpl

class MainViewModel(
    private val liveDataObserver: MutableLiveData<AppState> = MutableLiveData(),
    private val repository: Repository = RepositoryImpl(RemoteDataSource()),
    private val localRepository: LocalRepositoryImpl = LocalRepositoryImpl(
        App.getMovieDao(),
        App.getFavoriteMovieDao(),
        App.getNowPlayingMovieDao(),
        App.getUpcomingMovieDao()
    )
) : ViewModel() {
    fun getLiveData() = liveDataObserver

    fun saveNowPlayingMoviesToDb(movies: List<MovieDTO>) = localRepository.saveNowPlayingMovies(movies)
    fun deleteNowPlayingMovies() = localRepository.deleteNowPlayingMovies()
    fun showNowPlayingMovieByTitle(movieTitle: String) = localRepository.showNowPlayingMovieByTitle(movieTitle)
    fun getNowPlayingMoviesFromDb() {
        liveDataObserver.value = AppState.SuccessNow(localRepository.getAllNowPlayingMovies())
    }

    fun saveUpcomingMoviesToDb(movies: List<MovieDTO>) = localRepository.saveUpcomingMovies(movies)
    fun deleteUpcomingMovies() = localRepository.deleteUpcomingMovies()
    fun showUpcomingMovieByTitle(movieTitle: String) = localRepository.showUpcomingMovieByTitle(movieTitle)
    fun getUpcomingMoviesFromDb() {
        liveDataObserver.value = AppState.SuccessUpcoming(localRepository.getAllUpcomingMovies())
    }

    fun getMoviesFromRemoteSource(api: String, language: String, isNow: Boolean) {
        liveDataObserver.postValue(AppState.Loading)
        if(isNow) repository.getMovieFromServerNow(api, language, callBackNow)
        else repository.getMovieFromServerUpcoming(api, language, callBackUpcoming)
    }

    private val callBackNow = object : Callback<NowPlayingDTO> {
        override fun onResponse(call: Call<NowPlayingDTO>, response: Response<NowPlayingDTO>) {
            val serverResponse: NowPlayingDTO? = response.body()
            if (response.isSuccessful && serverResponse != null) {
                liveDataObserver.value = AppState.SuccessNow(serverResponse.results)
            } else {
                liveDataObserver.postValue(AppState.Error(NullPointerException()))
            }
        }
        override fun onFailure(call: Call<NowPlayingDTO>, t: Throwable) {
            liveDataObserver.postValue(AppState.Error(NullPointerException())) //TODO что-то адекватное
        }
    }

    private val callBackUpcoming = object : Callback<UpcomingDTO> {
        override fun onResponse(call: Call<UpcomingDTO>, response: Response<UpcomingDTO>) {
            val serverResponse: UpcomingDTO? = response.body()
            if (response.isSuccessful && serverResponse != null) {
                liveDataObserver.value = AppState.SuccessUpcoming(serverResponse.results)
            } else {
                liveDataObserver.postValue(AppState.Error(NullPointerException()))
            }
        }
        override fun onFailure(call: Call<UpcomingDTO>, t: Throwable) {
            liveDataObserver.postValue(AppState.Error(NullPointerException())) //TODO что-то адекватное
        }
    }
}