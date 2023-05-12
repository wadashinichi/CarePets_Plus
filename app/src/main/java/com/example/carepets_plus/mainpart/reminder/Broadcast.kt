package com.example.carepets_plus.mainpart.reminder

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.example.carepets_plus.R

const val notificationID = 1
const val channelID = "channel1"
const val titleExtra = "titleExtra"
const val messageExtra = "messageExtra"

class Broadcast : BroadcastReceiver() {

    private lateinit var context: Context
    lateinit var intent: Intent
    override fun onReceive(p0: Context?, p1: Intent?) {
        if (p0 != null) {
            context = p0
        }
        if (p1 != null) {
            intent = p1
        }
        val notification = NotificationCompat.Builder(context, channelID)
            .setSmallIcon(R.drawable.ic_android)
            .setContentTitle(intent.getStringExtra(titleExtra))
            .setContentText(intent.getStringExtra(titleExtra))
            .build()
        val manage = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manage.notify(notificationID, notification)
    }

}