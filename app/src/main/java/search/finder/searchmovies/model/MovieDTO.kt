package search.finder.searchmovies.model

data class NowPlayingDTO(val results:List<MovieDTO>)
data class UpcomingDTO(val results:List<MovieDTO>)

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
)