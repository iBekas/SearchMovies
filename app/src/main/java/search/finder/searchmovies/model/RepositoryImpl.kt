package search.finder.searchmovies.model

class RepositoryImpl: Repository{

    override fun getMovieFromServerNow(): List<MovieDTO> {
        TODO("Not yet implemented")
    }

    override fun getMovieFromServerUpcoming(): List<MovieDTO> {
        TODO("Not yet implemented")
    }


    override fun getMovieFromLocalNow() = getMoviesNow()
    override fun getMovieFromLocalUpcoming() = getMoviesUpcoming()
}