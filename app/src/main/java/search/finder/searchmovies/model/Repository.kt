package search.finder.searchmovies.model

interface Repository {
    fun getMovieFromServerNow(): List<MovieDTO>
    fun getMovieFromServerUpcoming(): List<MovieDTO>
    fun getMovieFromLocalNow(): List<Movie>
    fun getMovieFromLocalUpcoming(): List<Movie>
}