package search.finder.searchmovies.model

import search.finder.searchmovies.view.MainFragment
import search.finder.searchmovies.viewmodel.MovieLoader
import search.finder.searchmovies.viewmodel.MovieLoaderListener

class RepositoryImpl: Repository{
    override fun getMovieFromServerNow(): List<MovieDTO> {
        return MovieLoader(MainFragment as MovieLoaderListener).loadNowPlaying()
    }

    override fun getMovieFromServerUpcoming(): List<MovieDTO> {
        TODO("Not yet implemented")
    }

    override fun getMovieFromLocalNow() = getMoviesNow()
    override fun getMovieFromLocalUpcoming() = getMoviesUpcoming()
}