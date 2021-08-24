package search.finder.searchmovies.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import search.finder.searchmovies.app.App
import search.finder.searchmovies.model.MovieDetailsDTO
import search.finder.searchmovies.repository.LocalRepositoryImpl

class HistoryAndFavoriteMovieViewModel(
    private val liveDataObserver: MutableLiveData<AppState> = MutableLiveData(),
    private val localRepository: LocalRepositoryImpl = LocalRepositoryImpl(
        App.getMovieDao(),
        App.getFavoriteMovieDao(),
        App.getMainMovieDao()
    )
) : ViewModel() {
    fun getLiveData() = liveDataObserver

    fun getAllMoviesHistory() {
        liveDataObserver.value = AppState.SuccessHistory(localRepository.getAllMoviesHistory())
    }

    fun getAllMoviesFavorite() {
        liveDataObserver.value = AppState.SuccessFavorite(localRepository.getAllFavoriteMovies())
    }

    fun deleteMoviesHistory() = localRepository.deleteMoviesHistory()

    fun showHistoryMovieByTitle(movieTitle: String) = localRepository.showHistoryMovieByTitle(movieTitle)

    fun showFavoriteMovieByTitle(movieTitle: String) = localRepository.showFavoriteMovieByTitle(movieTitle)
}