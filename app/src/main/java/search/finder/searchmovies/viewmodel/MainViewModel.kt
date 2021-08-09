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
    with(liveDataObserver) {
        with(repository) {
            postValue(AppState.Loading)
            if (isNow)
                Thread {
                    Thread.sleep(2100)
                    postValue(AppState.SuccessNew(getMovieFromServerNow()))
                }.start()
            else
                Thread {
                    Thread.sleep(2000)
                    postValue(AppState.SuccessOld(getMovieFromServerUpcoming()))
                }.start()
        }
    }
}

    /* Вариант с одним потоком
    private fun getDataFromLocalSource(isNow: Boolean) {
        val lock = ReentrantLock()
        liveDataObserver.postValue(AppState.Loading)
        Thread {
            try {
                lock.lock()
                Thread.sleep(2000)
                with(liveDataObserver) {
                    with(repository) {
                        if (isNow) postValue(AppState.SuccessNew(getMovieFromLocalNow()))
                        else postValue(AppState.SuccessOld(getMovieFromLocalUpcoming()))
                    }
                }
            } finally {
                lock.unlock()
            }
        }.start()
    }*/
}