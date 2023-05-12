package com.example.carepets_plus.mainpart.reminder

import android.Manifest
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.carepets_plus.R

const val notificationID = 1
const val channelID = "petsCareChannel"
const val titleExtra = "titleExtra"
const val messageExtra = "messageExtra"

class Broadcast : BroadcastReceiver() {

    private lateinit var context: Context
    lateinit var intent: Intent
    override fun onReceive(p0: Context?, p1: Intent?) {
        if (p0 != null) {
            context = p0
        }
        var builder = NotificationCompat.Builder(context, "petsCareChannel")
            .setSmallIcon(R.drawable.ic_pet_foot)
            .setContentTitle("Remind ...")
            .setContentText("Hey, this the time for ...")
            .setPriority(androidx.core.app.NotificationCompat.PRIORITY_DEFAULT)

        val notificationManager: NotificationManagerCompat = NotificationManagerCompat.from(context)
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            return
        }
        notificationManager.notify(200, builder.build())
    }

}