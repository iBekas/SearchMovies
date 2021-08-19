package search.finder.searchmovies.utils

import search.finder.searchmovies.model.MovieDTO
import search.finder.searchmovies.room.FavoriteMovieEntity
import search.finder.searchmovies.room.MovieEntity

fun convertEntityToMovieHistory(movieEntity: List<MovieEntity>): List<MovieDTO> {
    return movieEntity.map {
        MovieDTO(
            it.adult!!,
            "",
            null,
            it.movieId!!,
            "",
            "",
            it.overview,
            0.0,
            it.image!!,
            it.releaseDate,
            it.title,
            false,
            it.voteAverage,
        )
    }
}

fun convertEntityToMovieFavorite(movieEntity: List<FavoriteMovieEntity>): List<MovieDTO> {
    return movieEntity.map {
        MovieDTO(
            it.adult!!,
            "",
            null,
            it.movieId!!,
            "",
            "",
            it.overview,
            0.0,
            it.image!!,
            it.releaseDate,
            it.title,
            false,
            it.voteAverage,
        )
    }
}

fun convertMovieHistoryToEntity(movie: MovieDTO): MovieEntity {
    return MovieEntity(
        movie.id,
        movie.release_date,
        movie.poster_path,
        movie.title,
        movie.overview,
        movie.vote_average,
        movie.adult
    )
}

fun convertMovieFavoriteToEntity(movie: MovieDTO): FavoriteMovieEntity {
    return FavoriteMovieEntity(
        movie.id,
        movie.release_date,
        movie.poster_path,
        movie.title,
        movie.overview,
        movie.vote_average,
        movie.adult
    )
}