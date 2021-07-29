package search.finder.searchmovies.viewmodel

import search.finder.searchmovies.model.Movie

sealed class AppState {
    data class Success(val dataWeather: Movie):AppState()
    data class Error( val error:Throwable):AppState()
    object Loading: AppState()
}