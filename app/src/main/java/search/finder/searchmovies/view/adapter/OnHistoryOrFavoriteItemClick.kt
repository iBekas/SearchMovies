package search.finder.searchmovies.view.adapter

import search.finder.searchmovies.model.MovieDetailsDTO

interface OnHistoryOrFavoriteItemClick {
    fun onItemHistoryClick(movie: MovieDetailsDTO)
}