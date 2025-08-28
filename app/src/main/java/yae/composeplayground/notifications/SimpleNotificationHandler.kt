package yae.composeplayground.notifications

import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import yae.composeplayground.R
import kotlin.random.Random

class SimpleNotificationHandler(private val context:Context) {
    private val manager = context.getSystemService(NotificationManager::class.java)
    private val channelId = "cmp_playground"

    fun showSimpleNotification() {
        val notification = NotificationCompat.Builder(context, channelId)
            .setContentText("Simple notification text")
            .setContentInfo("Simple notification info")
            .setContentTitle("Simple title")
            .setAutoCancel(true)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setPriority(NotificationManager.IMPORTANCE_HIGH)
            .build()
        manager.notify(Random.nextInt(), notification)
    }
}