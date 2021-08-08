package search.finder.searchmovies.view.adapter

import search.finder.searchmovies.model.MovieDTO

interface OnItemViewClickListener {
    fun onItemViewClick(movie: MovieDTO)
}