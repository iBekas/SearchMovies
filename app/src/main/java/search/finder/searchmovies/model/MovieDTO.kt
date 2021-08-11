package search.finder.searchmovies.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class NowPlayingDTO(val results:ArrayList<MovieDTO>): Parcelable

@Parcelize
data class UpcomingDTO(val results:ArrayList<MovieDTO>): Parcelable

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
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Long
): Parcelable


