package search.finder.searchmovies.view.adapter

import search.finder.searchmovies.model.Movie

interface OnItemViewClickListener {
    fun onItemViewClick(movie: Movie)
}