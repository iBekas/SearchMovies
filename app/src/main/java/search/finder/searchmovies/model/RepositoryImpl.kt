package search.finder.searchmovies.model

class RepositoryImpl: Repository{
    override fun getMovieFromServer() = Movie()
    override fun getMovieFromLocalNow() = getMoviesNow()
    override fun getMovieFromLocalUpcoming() = getMoviesUpcoming()
}