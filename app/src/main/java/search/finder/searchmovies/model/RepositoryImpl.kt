package search.finder.searchmovies.model

import search.finder.searchmovies.viewmodel.MovieLoader

class RepositoryImpl: Repository{

    override fun getMovieFromServerNow(): List<MovieDTO>{
//        MovieLoader().loadNowPlaying()
        return getMovieNowToList()
    }
    override fun getMovieFromServerUpcoming(): List<MovieDTO>{
//        MovieLoader().loadUpcoming()
        return getMovieUpcomingToList()
    }
    override fun getMovieFromLocalNow() = getMoviesNow()
    override fun getMovieFromLocalUpcoming() = getMoviesUpcoming()
}