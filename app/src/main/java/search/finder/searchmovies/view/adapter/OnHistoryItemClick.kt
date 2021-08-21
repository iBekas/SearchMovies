package search.finder.searchmovies.view.adapter

import search.finder.searchmovies.model.MovieDetailsDTO

interface OnHistoryItemClick {
    fun onItemHistoryClick(movie: MovieDetailsDTO)
}