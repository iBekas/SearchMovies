package search.finder.searchmovies.model

class RepositoryImpl: Repository{
    override fun getMovieFromServer(): Movie {
        return Movie();
    }

    override fun getMovieFromLocal(): Movie {
        return Movie();
    }
}