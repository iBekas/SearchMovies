package search.finder.searchmovies.viewmodel

import android.os.Handler
import com.google.gson.Gson
import search.finder.searchmovies.model.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class MovieLoader(){
    private val language = "ru-RU"

    fun loadNowPlaying(){
        val handler = Handler()
        Thread {
            try {
                val url =
                    URL("${TMDB_API_URL_NOW_PLAYING}?api_key=${TMDB_API_KEY_VALUE}&language=${language}")
                val httpsURLConnection: HttpsURLConnection =
                    url.openConnection() as HttpsURLConnection
                httpsURLConnection.connectTimeout = 5000
                httpsURLConnection.requestMethod = "GET"
                httpsURLConnection.addRequestProperty(TMDB_API_KEY_NAME, TMDB_API_KEY_VALUE)
                val buffer = BufferedReader(InputStreamReader(httpsURLConnection.inputStream))
                val nowPlayingDTO: NowPlayingDTO = Gson().fromJson(buffer, NowPlayingDTO::class.java)
                handler.post(Runnable { setMovieNowToList(nowPlayingDTO.results) })
            } catch (e: Exception){
                TODO("e")
            }
        }.start()
    }

    fun loadUpcoming(){
        val handler = Handler()
        Thread {
            try {
                val url =
                    URL("${TMDB_API_URL_UPCOMING}?api_key=${TMDB_API_KEY_VALUE}&language=${language}")
                val httpsURLConnection: HttpsURLConnection =
                    url.openConnection() as HttpsURLConnection
                httpsURLConnection.connectTimeout = 5000
                httpsURLConnection.requestMethod = "GET"
                httpsURLConnection.addRequestProperty(TMDB_API_KEY_NAME, TMDB_API_KEY_VALUE)
                val buffer = BufferedReader(InputStreamReader(httpsURLConnection.inputStream))
                val upcomingDTO: UpcomingDTO = Gson().fromJson(buffer, UpcomingDTO::class.java)
                handler.post(Runnable { setMovieUpcomingToList(upcomingDTO.results) })
            } catch (e: Exception){
                TODO("e")
            }
        }.start()
    }
}