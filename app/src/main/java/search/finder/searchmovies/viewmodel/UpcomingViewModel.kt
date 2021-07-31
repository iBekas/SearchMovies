package search.finder.searchmovies.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import search.finder.searchmovies.model.Repository
import search.finder.searchmovies.model.RepositoryImpl

class UpcomingViewModel(private val liveDataObserver : MutableLiveData<AppState> = MutableLiveData(),
                        private val repository: Repository = RepositoryImpl()
) : ViewModel() {
    fun getLiveData()=liveDataObserver

    fun getMovie()=getDataFromLocalSource()


    private fun getDataFromLocalSource(){
        Thread{
            liveDataObserver.postValue(AppState.Loading)
            Thread.sleep(2000)
            liveDataObserver.postValue(AppState.Success(repository.getMovieFromLocalUpcoming()))
        }.start()
    }
}