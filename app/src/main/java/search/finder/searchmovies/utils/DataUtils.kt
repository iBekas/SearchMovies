package search.finder.searchmovies.utils

import search.finder.searchmovies.model.MovieDTO
import search.finder.searchmovies.model.MovieDetailsDTO
import search.finder.searchmovies.room.FavoriteMovieEntity
import search.finder.searchmovies.room.MovieEntity
import search.finder.searchmovies.room.NowPlayingEntity
import search.finder.searchmovies.room.UpcomingEntity

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
fun convertEntityToMovieNowPlaying(movieEntity: List<NowPlayingEntity>): List<MovieDTO> {
    return movieEntity.map {
        MovieDTO(
            it.adult!!,
            "",
            null,
            it.movieId,
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
fun convertEntityToMovieUpcoming(movieEntity: List<UpcomingEntity>): List<MovieDTO> {
    return movieEntity.map {
        MovieDTO(
            it.adult!!,
            "",
            null,
            it.movieId,
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
fun convertMovieNowPlayingToEntity(movies: List<MovieDTO>): List<NowPlayingEntity> {
    return movies.map{
        NowPlayingEntity(
            it.id,
            it.release_date,
            it.poster_path,
            it.title,
            it.overview,
            it.vote_average,
            it.adult
        )
    }
}
fun convertMovieUpcomingToEntity(movies: List<MovieDTO>): List<UpcomingEntity> {
    return movies.map{
        UpcomingEntity(
            it.id,
            it.release_date,
            it.poster_path,
            it.title,
            it.overview,
            it.vote_average,
            it.adult
        )
    }
}
fun convertMovieDetailDtoToMovieDto(movie: MovieDetailsDTO): MovieDTO {
    return MovieDTO(
        movie.adult!!,
        "",
        null,
        movie.id,
        "",
        "",
        movie.overview,
        0.0,
        movie.poster_path,
        movie.release_date,
        movie.title,
        false,
        movie.vote_average,
    )
}