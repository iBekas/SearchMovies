package search.finder.searchmovies.model

interface Repository {
    fun getMovieFromServer(): Movie
    fun getMovieFromLocalNow(): List<Movie>
    fun getMovieFromLocalUpcoming(): List<Movie>
}