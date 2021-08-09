package search.finder.searchmovies.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class NowPlayingDTO(val results:List<MovieDTO>)
data class UpcomingDTO(val results:List<MovieDTO>)

@Parcelize
data class MovieDTO (
    val adult: Boolean,
    val backdropPath: String,
    val genreIDS: List<Long>,
    val id: Long,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Long
): Parcelable

lateinit var movieNow: List<MovieDTO>
fun setMovieNowToList(movieDTO: List<MovieDTO>){
    movieNow = movieDTO
}
fun getMovieNowToList(): List<MovieDTO> = movieNow


lateinit var movieUpcoming: List<MovieDTO>
fun setMovieUpcomingToList(movieDTO: List<MovieDTO>){
    movieUpcoming = movieDTO
}
fun getMovieUpcomingToList(): List<MovieDTO> = movieUpcoming
