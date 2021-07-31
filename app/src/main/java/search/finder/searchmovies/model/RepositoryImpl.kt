package search.finder.searchmovies.model

class RepositoryImpl: Repository{
    override fun getMovieFromServer(): Movie {
        return Movie()
    }

    override fun getMovieFromLocalNow(): List<Movie> {
        return getMoviesNow()
    }

    override fun getMovieFromLocalUpcoming(): List<Movie> {
        return getMoviesUpcoming()
    }

}