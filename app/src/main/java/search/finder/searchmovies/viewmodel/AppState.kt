package search.finder.searchmovies.viewmodel

import search.finder.searchmovies.model.MovieDTO

sealed class AppState {
    data class SuccessOld(val dataMovies: List<MovieDTO>):AppState()
    data class SuccessNew(val dataMovies: List<MovieDTO>):AppState()
    data class Error( val error:Throwable):AppState()
    object Loading: AppState()
}