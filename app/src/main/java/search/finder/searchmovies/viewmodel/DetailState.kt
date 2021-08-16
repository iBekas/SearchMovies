package search.finder.searchmovies.viewmodel

import search.finder.searchmovies.model.MovieDetailsDTO

sealed class DetailState{
    data class Success(val dataMovie: MovieDetailsDTO):DetailState()
    data class Error( val error:Throwable):DetailState()
}
