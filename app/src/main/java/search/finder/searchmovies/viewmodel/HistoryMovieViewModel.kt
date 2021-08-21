package search.finder.searchmovies.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import search.finder.searchmovies.app.App
import search.finder.searchmovies.repository.LocalRepositoryImpl

class HistoryMovieViewModel(
    private val liveDataObserver: MutableLiveData<AppState> = MutableLiveData(),
    private val historyRepository: LocalRepositoryImpl = LocalRepositoryImpl(App.getMovieDao(), App.getFavoriteMovieDao()))
    : ViewModel() {
        fun getLiveData() = liveDataObserver


        fun getAllMoviesHistory(){
            liveDataObserver.value = AppState.SuccessHistory(historyRepository.getAllMoviesHistory())
        }

        fun deleteMoviesHistory() {
            historyRepository.deleteMoviesHistory()
//            liveDataObserver.value = AppState.SuccessHistory(historyRepository.getAllMoviesHistory())
        }

}