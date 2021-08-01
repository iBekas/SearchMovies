package search.finder.searchmovies.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import search.finder.searchmovies.model.Repository
import search.finder.searchmovies.model.RepositoryImpl

class MainViewModel(private val liveDataObserver : MutableLiveData<AppState> = MutableLiveData(),
                    private val repository: Repository = RepositoryImpl()
) : ViewModel() {
    fun getLiveData()=liveDataObserver

    fun getMovieNow()=getDataFromLocalSource(true)
    fun getMovieUpcoming()=getDataFromLocalSource(false)

    fun clearLiveData(){
        liveDataObserver.postValue(null)
    }


    private fun getDataFromLocalSource(isNow: Boolean){
        Thread{
            liveDataObserver.postValue(AppState.Loading)
            Thread.sleep(2000)
            liveDataObserver.postValue(AppState.Success(if(isNow) repository.getMovieFromLocalNow() else repository.getMovieFromLocalUpcoming()))
        }.start()
    }
}