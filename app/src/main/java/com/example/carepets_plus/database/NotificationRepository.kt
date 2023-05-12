package com.example.carepets_plus.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor

class NotificationRepository(context: Context) {
    private var dbHelper: CarePetDatabase
    init {
        dbHelper = CarePetDatabase(context)
    }
    fun insertNotification(notification: Notification) {
        val db =  dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("pet_ID", notification.petId)
            put("notification_name", notification.name)
            put("notification_time", notification.time)
            put("notification_state", notification.state)
        }
        db.insert("Notifications", null, values)
    }
    fun updateNotification(notification: Notification) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("notification_name", notification.name)
            put("notification_time", notification.time)
            put("notification_state", notification.state)
        }
        db.update("Notifications", values, "notification_name LIKE ?", arrayOf("${notification.name}"))
    }
    fun getNotificationByName(name: String): Notification? {
        val db = dbHelper.readableDatabase
        val cursor: Cursor = db.query("Notifications", null, "notification_name = ?", arrayOf("$name"), null, null, null)
        if (cursor.count > 0) {
            cursor.moveToFirst()
            return Notification(cursor.getInt(cursor.getColumnIndexOrThrow("notification_ID")),
                cursor.getInt(cursor.getColumnIndexOrThrow("pet_ID")),
                cursor.getString(cursor.getColumnIndexOrThrow("notification_name")),
                cursor.getString(cursor.getColumnIndexOrThrow("notification_time")),
                cursor.getInt(cursor.getColumnIndexOrThrow("notification_state")))
        }
        cursor.close()
        return null
    }
}