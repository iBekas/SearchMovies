package search.finder.searchmovies.room

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

    @Parcelize
    @Entity(tableName = "table_favorite_movies")
    data class FavoriteMovieEntity(
        @PrimaryKey
        val movieId: Long,
        @ColumnInfo(name = "release_date")
        val releaseDate: String = "",
        @ColumnInfo(name = "poster_path")
        val image: String? = "",
        @ColumnInfo(name = "title")
        val title: String = "",
        @ColumnInfo(name = "overview")
        val overview: String = "",
        @ColumnInfo(name = "vote_average")
        val voteAverage: Double = 0.0,
        @ColumnInfo(name = "adult")
        val adult: Boolean
    ): Parcelable

    @Parcelize
    @Entity(tableName = "table_movies")
    data class MovieEntity(
        @PrimaryKey
        val movieId: Long,
        @ColumnInfo(name = "release_date")
        val releaseDate: String = "",
        @ColumnInfo(name = "poster_path")
        val image: String? = "",
        @ColumnInfo(name = "title")
        val title: String = "",
        @ColumnInfo(name = "overview")
        val overview: String = "",
        @ColumnInfo(name = "vote_average")
        val voteAverage: Double = 0.0,
        @ColumnInfo(name = "adult")
        val adult: Boolean
    ): Parcelable

@Parcelize
@Entity(tableName = "table_now_playing_movies")
data class NowPlayingEntity(
    @PrimaryKey
    val movieId: Long,
    @ColumnInfo(name = "release_date")
    val releaseDate: String = "",
    @ColumnInfo(name = "poster_path")
    val image: String? = "",
    @ColumnInfo(name = "title")
    val title: String = "",
    @ColumnInfo(name = "overview")
    val overview: String = "",
    @ColumnInfo(name = "vote_average")
    val voteAverage: Double = 0.0,
    @ColumnInfo(name = "adult")
    val adult: Boolean
): Parcelable
