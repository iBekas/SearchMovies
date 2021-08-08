package search.finder.searchmovies.viewmodel

import search.finder.searchmovies.model.MovieDTO

interface MovieLoaderListener {
    fun onLoaded(movieDTO: List<MovieDTO>)
    fun onFailed(throwable: Throwable)
}