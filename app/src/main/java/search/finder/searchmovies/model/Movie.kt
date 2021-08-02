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
        Movie(1, "Фильм1", 2000, 4.1, null, textDes()),
        Movie(1, "Фильм2", 2001, 4.2, null, textDes()),
        Movie(1, "Фильм3", 2002, 4.3, null, textDes()),
        Movie(1, "Фильм4", 2003, 4.4, null, textDes()),
        Movie(1, "Фильм5", 2004, 4.5, null, textDes()),
        Movie(1, "Фильм6", 2005, 4.6, null, textDes()),
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

fun textDes(): String{
    return "Всего один легкомысленный поступок может привести к тому, что собственную свадьбу придется праздновать на небесах. Вадик даже не догадывался, что, желая сделать праздник веселее, он попадет в Чистилище, где предстанет перед Небесной канцелярией и будет отчитываться за всю свою короткую, но насыщенную жизнь. Как доказать богам, что ты хороший, если у них записан каждый твой шаг?\n" +
            "\n" +
            "Вадик пытается вспомнить о себе хоть что-то хорошее, но память его все время подводит. На восьмилетие он умудрился напиться, в армии устроил оргию, а в 90-е стал таким конкретным бизнесменом, что теперь ему в концепцию рая вписаться абсолютно нереально. Впрочем, есть и позитивный момент: своей жизнью он насмешил Бога, значит, еще не все потеряно."
}

