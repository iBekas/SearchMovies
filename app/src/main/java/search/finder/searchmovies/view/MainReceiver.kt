package search.finder.searchmovies.view

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Settings
import android.widget.Toast

class MainReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val onAir: Boolean = isAirplaneModeOn(context)
        if (onAir) Toast.makeText(
            context,
            "Спасибо! Теперь могу загружать фильмы",
            Toast.LENGTH_SHORT
        )
            .show()
        else Toast.makeText(context, "Выключи! Я же не смогу загрузить фильмы.", Toast.LENGTH_SHORT)
            .show()
    }

    private fun isAirplaneModeOn(context: Context): Boolean {
        return Settings.System.getInt(
            context.contentResolver,
            Settings.Global.AIRPLANE_MODE_ON, 0
        ) == 0
    }
}