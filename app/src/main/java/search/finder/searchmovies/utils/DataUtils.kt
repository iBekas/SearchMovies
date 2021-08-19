package search.finder.searchmovies.utils

import search.finder.searchmovies.model.*
import search.finder.searchmovies.room.FavoriteMovieEntity
import search.finder.searchmovies.room.MovieEntity

fun convertEntityToMovieHistory(movieEntity: List<MovieEntity>): List<MovieDetailsDTO> {
    return movieEntity.map {
        MovieDetailsDTO(
            it.adult,
        "",
        null,
        0,
        null,
        "",
        it.movieId,
        "",
        "",
        "",
        it.overview,
        0.0,
        it.image!!,
        null,
        null,
        it.releaseDate,
        0,
        0,
        null,
        "",
        "",
        it.title,
        false,
        it.voteAverage,
        0
        )
    }
}

fun convertEntityToMovieFavorite(movieEntity: List<FavoriteMovieEntity>): List<MovieDetailsDTO> {
    return movieEntity.map {
        MovieDetailsDTO(
            it.adult,
            "",
            null,
            0,
            null,
            "",
            it.movieId,
            "",
            "",
            "",
            it.overview,
            0.0,
            it.image!!,
            null,
            null,
            it.releaseDate,
            0,
            0,
            null,
            "",
            "",
            it.title,
            false,
            it.voteAverage,
            0
        )
    }
}

fun convertMovieHistoryToEntity(movie: MovieDetailsDTO): MovieEntity {
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

fun convertMovieFavoriteToEntity(movie: MovieDetailsDTO): FavoriteMovieEntity {
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