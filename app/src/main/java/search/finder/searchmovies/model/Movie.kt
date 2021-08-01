package search.finder.searchmovies.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val img: Int = 1,
    val title: String = "Лучший фильм",
    val releaseYear: Int? = 2020,
    val vote: Double? = 4.5,
    val expectData: String? = "01.01.2022",
    val movieDescription: String = "Описание фильма"
): Parcelable

fun getMoviesNow(): List<Movie> {
    return listOf(
        Movie(1, "Фильм1", 2000, 4.1, null, "Описание фильма"),
        Movie(1, "Фильм2", 2001, 4.2, null, "Описание фильма"),
        Movie(1, "Фильм3", 2002, 4.3, null, "Описание фильма"),
        Movie(1, "Фильм4", 2003, 4.4, null, "Описание фильма"),
        Movie(1, "Фильм5", 2004, 4.5, null, "Описание фильма"),
        Movie(1, "Фильм6", 2005, 4.6, null, "Описание фильма"),
    )
}

fun getMoviesUpcoming(): List<Movie> {
    return listOf(
        Movie(1, "Фильм7", null, null, "01.01.2022", "Описание фильма"),
        Movie(1, "Фильм8", null, null, "02.01.2022", "Описание фильма"),
        Movie(1, "Фильм9", null, null, "03.01.2022", "Описание фильма"),
        Movie(1, "Фильм10", null, null, "04.01.2022", "Описание фильма"),
        Movie(1, "Фильм11", null, null, "05.01.2022", "Описание фильма"),
        Movie(1, "Фильм12", null, null, "06.01.2022", "Описание фильма"),
    )
}

