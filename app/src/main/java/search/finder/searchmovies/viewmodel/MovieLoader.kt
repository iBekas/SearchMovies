package search.finder.searchmovies.viewmodel

import android.os.Handler
import com.google.gson.Gson
import search.finder.searchmovies.model.*
import search.finder.searchmovies.view.MainFragment
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.util.concurrent.locks.ReentrantLock
import javax.net.ssl.HttpsURLConnection

class MovieLoader(  private val listener: MainFragment.MovieLoaderListener){
    private val language = "ru-RU"
    private val lock = ReentrantLock()

    fun loadNowPlaying(){
        val handler = Handler()
        Thread {
                lock.lock()
                val url =
                    URL("${TMDB_API_URL_NOW_PLAYING}?api_key=${TMDB_API_KEY_VALUE}&language=${language}")
                val httpsURLConnection: HttpsURLConnection =
                    url.openConnection() as HttpsURLConnection
                httpsURLConnection.connectTimeout = 5000
                httpsURLConnection.requestMethod = "GET"
                httpsURLConnection.addRequestProperty(TMDB_API_KEY_NAME, TMDB_API_KEY_VALUE)
                val buffer = BufferedReader(InputStreamReader(httpsURLConnection.inputStream))
                val nowPlayingDTO: NowPlayingDTO = Gson().fromJson(buffer, NowPlayingDTO::class.java)
                handler.post(Runnable { listener.onLoadedNow(nowPlayingDTO) })
        }.start()
    }

    fun loadUpcoming(){
        val handler = Handler()
        Thread {
                val url =
                    URL("${TMDB_API_URL_UPCOMING}?api_key=${TMDB_API_KEY_VALUE}&language=${language}")
                val httpsURLConnection: HttpsURLConnection =
                    url.openConnection() as HttpsURLConnection
                httpsURLConnection.connectTimeout = 5000
                httpsURLConnection.requestMethod = "GET"
                httpsURLConnection.addRequestProperty(TMDB_API_KEY_NAME, TMDB_API_KEY_VALUE)
                val buffer = BufferedReader(InputStreamReader(httpsURLConnection.inputStream))
                val upcomingDTO: UpcomingDTO = Gson().fromJson(buffer, UpcomingDTO::class.java)
                handler.post(Runnable { listener.onLoadedUpcoming(upcomingDTO) })
        }.start()
    }
}