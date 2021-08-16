package search.finder.searchmovies.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class NowPlayingDTO(val results:List<MovieDTO>)

data class UpcomingDTO(val results:List<MovieDTO>)

@Parcelize
data class MovieDTO (
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: List<Long>,
    val id: Long,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Long
): Parcelable

@Parcelize
data class MovieDetailsDTO (
    val adult: Boolean,
    val backdrop_path: String,
    val belongs_to_collection: BelongsToCollection,
    val budget: Long,
    val genres: List<Genre>,
    val homepage: String,
    val id: Long,
    val imdb_id: String,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val production_companies: List<ProductionCompany>,
    val production_countries: List<ProductionCountry>,
    val release_date: String,
    val revenue: Long,
    val runtime: Long,
    val spoken_languages: List<SpokenLanguage>,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Long
) : Parcelable

@Parcelize
data class BelongsToCollection (
    val id: Long,
    val name: String,
    val poster_path: String,
    val backdrop_path: String
): Parcelable

@Parcelize
data class Genre (
    val id: Long,
    val name: String
): Parcelable

@Parcelize
data class ProductionCompany (
    val id: Long,
    val logo_path: String? = null,
    val name: String,
    val origin_country: String
): Parcelable

@Parcelize
data class ProductionCountry (
    val iso_3166_1: String,
    val name: String
): Parcelable

@Parcelize
data class SpokenLanguage (
    val english_name: String,
    val iso_639_1: String,
    val name: String
): Parcelable

