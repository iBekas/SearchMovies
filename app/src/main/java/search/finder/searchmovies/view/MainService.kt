package search.finder.searchmovies.view

import android.app.IntentService
import android.content.Intent
import android.os.Handler
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.gson.Gson
import search.finder.searchmovies.model.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.net.URL
import java.util.concurrent.TimeUnit
import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock
import javax.net.ssl.HttpsURLConnection

const val MAIN_INTENT_FILTER = "MAIN INTENT FILTER"
const val MAIN_LOAD_RESULT_EXTRA = "LOAD RESULT"
const val MAIN_RESPONSE_SUCCESS_NOW_EXTRA = "RESPONSE SUCCESS NOW"
const val MAIN_RESPONSE_SUCCESS_UPCOMING_EXTRA = "RESPONSE SUCCESS UPCOMING"
const val MAIN_MOVIE_NOW_LIST_EXTRA = "MOVIE NOW LIST"
const val MAIN_MOVIE_UPCOMING_LIST_EXTRA = "MOVIE UPCOMING LIST"
const val LANGUAGE_EXTRA = "Language"
private const val REQUEST_GET = "GET"
private const val REQUEST_TIMEOUT = 10000

class MainService(name: String = "service") : IntentService(name) {

    private val broadcastIntent = Intent(MAIN_INTENT_FILTER)

    override fun onHandleIntent(intent: Intent?) {
        intent?.let {
            loadNowPlaying(it.getStringExtra(LANGUAGE_EXTRA) ?: "en-US")
            loadUpcoming(it.getStringExtra(LANGUAGE_EXTRA) ?: "en-US")
        }

    }

    private fun loadNowPlaying(language: String) {
        Thread {
                val url =
                    URL("$TMDB_API_URL_NOW_PLAYING?api_key=$TMDB_API_KEY_VALUE&language=${language}")
                val httpsURLConnection: HttpsURLConnection =
                    url.openConnection() as HttpsURLConnection
                httpsURLConnection.connectTimeout = REQUEST_TIMEOUT
                httpsURLConnection.requestMethod = REQUEST_GET
                httpsURLConnection.addRequestProperty(TMDB_API_KEY_NAME, TMDB_API_KEY_VALUE)
                val buffer = BufferedReader(InputStreamReader(httpsURLConnection.inputStream))
                val nowPlayingDTO: NowPlayingDTO =
                    Gson().fromJson(buffer, NowPlayingDTO::class.java)
                putLoadResult(MAIN_RESPONSE_SUCCESS_NOW_EXTRA)
                broadcastIntent.putParcelableArrayListExtra(
                    MAIN_MOVIE_NOW_LIST_EXTRA,
                    nowPlayingDTO.results
                )
                LocalBroadcastManager.getInstance(this).sendBroadcast(broadcastIntent)
        }.start()
    }

    private fun loadUpcoming(language: String) {
        Thread {
                val url =
                    URL("$TMDB_API_URL_UPCOMING?api_key=$TMDB_API_KEY_VALUE&language=${language}")
                val httpsURLConnection: HttpsURLConnection =
                    url.openConnection() as HttpsURLConnection
                httpsURLConnection.connectTimeout = REQUEST_TIMEOUT
                httpsURLConnection.requestMethod = REQUEST_GET
                httpsURLConnection.addRequestProperty(TMDB_API_KEY_NAME, TMDB_API_KEY_VALUE)
                val buffer = BufferedReader(InputStreamReader(httpsURLConnection.inputStream))
                val upcomingDTO: UpcomingDTO = Gson().fromJson(buffer, UpcomingDTO::class.java)
                putLoadResult(MAIN_RESPONSE_SUCCESS_UPCOMING_EXTRA)
                broadcastIntent.putParcelableArrayListExtra(
                    MAIN_MOVIE_UPCOMING_LIST_EXTRA,
                    upcomingDTO.results
                )
                LocalBroadcastManager.getInstance(this).sendBroadcast(broadcastIntent)
        }.start()
    }

    private fun putLoadResult(result: String) {
        broadcastIntent.putExtra(MAIN_LOAD_RESULT_EXTRA, result)
    }
}

