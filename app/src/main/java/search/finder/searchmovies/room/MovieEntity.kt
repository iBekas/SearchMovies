package search.finder.searchmovies.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

    @Entity(tableName = "table_favorite_movies")
    data class FavoriteMovieEntity(
        @PrimaryKey
        val movieId: Int,
        @ColumnInfo(name = "release_date")
        val releaseDate: String = "",
        @ColumnInfo(name = "poster_path")
        val image: String? = "",
        @ColumnInfo(name = "title")
        val name: String = "",
        @ColumnInfo(name = "overview")
        val overview: String = "",
        @ColumnInfo(name = "vote_average")
        val voteAverage: Double = 0.0,
        @ColumnInfo(name = "adult")
        val adult: Boolean
    )

    @Entity(tableName = "table_movies")
    data class MovieEntity(
        @PrimaryKey
        val movieId: Int,
        @ColumnInfo(name = "release_date")
        val releaseDate: String = "",
        @ColumnInfo(name = "poster_path")
        val image: String? = "",
        @ColumnInfo(name = "title")
        val name: String = "",
        @ColumnInfo(name = "overview")
        val overview: String = "",
        @ColumnInfo(name = "vote_average")
        val voteAverage: Double = 0.0,
        @ColumnInfo(name = "adult")
        val adult: Boolean
    )
