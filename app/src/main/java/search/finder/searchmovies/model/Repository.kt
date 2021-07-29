package search.finder.searchmovies.model

interface Repository {
    fun getMovieFromServer(): Movie
    fun getMovieFromLocal(): Movie
}