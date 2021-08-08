package search.finder.searchmovies.viewmodel

import android.os.Handler
import com.google.gson.Gson
import search.finder.searchmovies.model.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class MovieLoader(private val listener: MovieLoaderListener){
    private val handler = Handler()
    private val language = "ru-RU"

    fun loadNowPlaying(){
        Thread {
            try {
                val url = URL("${TMDB_API_URL_NOW_PLAYING}?api_key=${TMDB_API_KEY_VALUE}&language=${language}")
                val httpsURLConnection: HttpsURLConnection =
                    url.openConnection() as HttpsURLConnection
                httpsURLConnection.connectTimeout = 5000
                httpsURLConnection.requestMethod = "GET"
                httpsURLConnection.addRequestProperty(TMDB_API_KEY_NAME, TMDB_API_KEY_VALUE)
                val buffer = BufferedReader(InputStreamReader(httpsURLConnection.inputStream))
                val movieDTO: List<MovieDTO> = Gson().fromJson(buffer, MovieDTO::class.java)
                handler.post(Runnable { listener.onLoaded(movieDTO) })
            } catch (e: Exception) {
                handler.post(Runnable { listener.onFailed(e) })
            }
        }.start()
    }

    fun loadUpcoming(){
        Thread {
            try {
                val url = URL("${TMDB_API_URL_UPCOMING}?api_key=${TMDB_API_KEY_VALUE}&language=${language}")
                val httpsURLConnection: HttpsURLConnection =
                    url.openConnection() as HttpsURLConnection
                httpsURLConnection.connectTimeout = 5000
                httpsURLConnection.requestMethod = "GET"
                httpsURLConnection.addRequestProperty(TMDB_API_KEY_NAME, TMDB_API_KEY_VALUE)
                val buffer = BufferedReader(InputStreamReader(httpsURLConnection.inputStream))
                val movieDTO: MovieDTO = Gson().fromJson(buffer, MovieDTO::class.java)
                handler.post(Runnable { listener.onLoaded(movieDTO) })
            } catch (e: Exception) {
                handler.post(Runnable { listener.onFailed(e) })
            }
        }.start()
    }
}