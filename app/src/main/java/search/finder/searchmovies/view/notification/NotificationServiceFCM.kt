package search.finder.searchmovies.view.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import search.finder.searchmovies.R

class NotificationServiceFCM : FirebaseMessagingService() {

    private val PUSH_KEY_TITLE = "title"
    private val PUSH_KEY_MESSAGE = "message"
    private val CHANNEL_ID = "movie_choice_channel"
    private val NOTIFICATION_ID= 1

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        val remoteMessageData = remoteMessage.data
        if(remoteMessageData.isNotEmpty()){
            handleDataMessage(remoteMessageData.toMap())
        }
    }

    private fun handleDataMessage(data: Map<String, String>) {
        val title = data[PUSH_KEY_TITLE]
        val message = data[PUSH_KEY_MESSAGE]
        if (!title.isNullOrBlank() && !message.isNullOrBlank()) {
            showNotification(title, message)
        }
    }

    private fun showNotification(title: String?, message: String?) {
        val notificationManager =
            getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val notificationBuilder = NotificationCompat.Builder(this, CHANNEL_ID).apply {
            setContentTitle(title)
            setContentText(message)
            setSmallIcon(R.drawable.ic_movies)
            priority = NotificationCompat.PRIORITY_DEFAULT
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel(notificationManager, title, message)
        }
        notificationManager.notify(NOTIFICATION_ID, notificationBuilder.build())
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createChannel(notificationManager: NotificationManager, title: String?, message: String?) {
        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel(CHANNEL_ID, title, importance).apply {
            description = message
        }
        notificationManager.createNotificationChannel(channel)
    }

    override fun onNewToken(token: String) {
        Log.d("myLogs",token)
    }
}