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
    private lateinit var intent: Intent
    override fun onReceive(p0: Context?, p1: Intent?) {
        if (p0 != null) {
            context = p0
        }
        if (p1 != null) {
            intent = p1
        }
        var notificationID: Int = 0
        val id = intent.getIntExtra("id", 0)
        var petName = intent.getStringExtra("petName")
        notificationID = when(intent.getStringExtra("name")) {
            "breakfast" -> 100 + id
            "lunch" -> 200 + id
            "dinner" -> 300 + id
            "snack" -> 400 + id
            "walk" -> 500 + id
            else -> 0 + id
        }
        var titleMessage = when(intent.getStringExtra("name")) {
            "breakfast" -> "Hey, this the time for $petName's Breakfast"
            "lunch" -> "Hey, this the time for $petName's Lunch."
            "dinner" -> "Hey, this the time for $petName's Dinner"
            "snack" -> "Hey, this the time for $petName's Snack"
            "walk" -> "Hey, this the time for $petName's Walk"
            else -> "Hey, this the time for other activity"
        }
        var builder = NotificationCompat.Builder(context, "petsCareChannel")
            .setSmallIcon(R.drawable.ic_pet_foot)
            .setContentTitle("Remind of PetsCare")
            .setContentText(titleMessage)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        val notificationManager: NotificationManagerCompat = NotificationManagerCompat.from(context)
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        notificationManager.notify(notificationID, builder.build())
    }

}