package search.finder.searchmovies.viewmodel

import search.finder.searchmovies.model.MovieCreditsDTO
import search.finder.searchmovies.model.MovieDTO
import search.finder.searchmovies.model.MovieDetailsDTO
import search.finder.searchmovies.model.PersonDTO

sealed class AppState {
    data class SuccessFavorite(val dataMovies: List<MovieDetailsDTO>):AppState()
    data class SuccessHistory(val dataMovies: List<MovieDetailsDTO>):AppState()
    data class SuccessNow(val dataMovies: List<MovieDTO>):AppState()
    data class SuccessUpcoming(val dataMovies: List<MovieDTO>):AppState()
    data class SuccessDetail(val dataMovie: MovieDetailsDTO):AppState()
    data class SuccessCredits(val dataMovie: MovieCreditsDTO):AppState()
    data class SuccessPerson(val dataMovie: PersonDTO):AppState()
    data class Error( val error:Throwable):AppState()
    object Loading: AppState()
}