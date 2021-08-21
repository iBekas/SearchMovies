package search.finder.searchmovies.viewmodel

import search.finder.searchmovies.model.MovieDTO
import search.finder.searchmovies.model.MovieDetailsDTO

sealed class AppState {
    data class SuccessNow(val dataMovies: List<MovieDTO>):AppState()
    data class SuccessUpcoming(val dataMovies: List<MovieDTO>):AppState()
    data class SuccessDetail(val dataMovie: MovieDetailsDTO):AppState()
    data class Error( val error:Throwable):AppState()
    object Loading: AppState()
}