package search.finder.searchmovies.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class NowPlayingDTO(val results:List<MovieDTO>)

data class UpcomingDTO(val results:List<MovieDTO>)

@Parcelize
data class MovieDTO(
    val adult: Boolean = false,
    val backdrop_path: String = "",
    val genre_ids: List<Long>? = null,
    val id: Long = 0,
    val original_language: String = "",
    val original_title: String = "",
    val overview: String = "",
    val popularity: Double = 0.0,
    val poster_path: String = "",
    val release_date: String = "",
    val title: String = "",
    val video: Boolean = false,
    val vote_average: Double = 0.0,
    val vote_count: Long = 0
): Parcelable {
    val isAdult
        get() = adult
}

@Parcelize
data class MovieDetailsDTO(
    val adult: Boolean,
    val backdrop_path: String,
    val belongs_to_collection: BelongsToCollection?,
    val budget: Long,
    val genres: List<Genre>?,
    val homepage: String,
    val id: Long,
    val imdb_id: String,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val production_companies: List<ProductionCompany>?,
    val production_countries: List<ProductionCountry>?,
    val release_date: String,
    val revenue: Long,
    val runtime: Long,
    val spoken_languages: List<SpokenLanguage>?,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Long
) : Parcelable {
    val isAdult
        get() = adult
}

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

@Parcelize
data class MovieCreditsDTO (
    val id: Long,
    val cast: List<Cast>,
    val crew: List<Cast>
): Parcelable

@Parcelize
data class Cast (
    val adult: Boolean,
    val gender: Long,
    val id: Long,
    val known_for_department: String,
    val name: String,
    val original_name: String,
    val popularity: Double,
    val profile_path: String? = null,
    val cast_id: Long? = null,
    val character: String? = null,
    val credit_id: String,
    val order: Long? = null,
    val department: String? = null,
    val job: String? = null
): Parcelable

@Parcelize
data class PersonDTO (
    val adult: Boolean,
    val also_known_as: List<String>,
    val biography: String,
    val birthday: String,
    val deathday: Any? = null,
    val gender: Long,
    val homepage: Any? = null,
    val id: Long,
    val imdb_id: String,
    val known_for_department: String,
    val name: String,
    val place_of_birth: String,
    val popularity: Double,
    val profile_path: String
): Parcelable