package search.finder.searchmovies.viewmodel

import search.finder.searchmovies.model.MovieDTO

interface MovieLoaderDetailsListener {
    fun onLoaded(movieDTO: List<MovieDTO>)
    fun onFailed(throwable: Throwable)
}