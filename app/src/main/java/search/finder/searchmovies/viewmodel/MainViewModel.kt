package search.finder.searchmovies.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import search.finder.searchmovies.model.Repository
import search.finder.searchmovies.model.RepositoryImpl

class MainViewModel(
    private val liveDataObserver: MutableLiveData<AppState> = MutableLiveData(),
    private val repository: Repository = RepositoryImpl()
) : ViewModel() {
    fun getLiveData() = liveDataObserver

    fun getMovieNow() = getDataFromLocalSource(true)
    fun getMovieUpcoming() = getDataFromLocalSource(false)

    private fun getDataFromLocalSource(isNow: Boolean) {
        liveDataObserver.postValue(AppState.Loading)
        if (isNow)
            Thread {
                Thread.sleep(2100)
                liveDataObserver.postValue(AppState.SuccessNew(repository.getMovieFromLocalNow()))
            }.start()
        else
            Thread {
                Thread.sleep(2000)
                liveDataObserver.postValue(AppState.SuccessOld(repository.getMovieFromLocalUpcoming()))
            }.start()
    }
}