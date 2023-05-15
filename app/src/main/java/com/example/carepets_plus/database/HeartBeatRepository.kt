package com.example.carepets_plus.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor

class HeartBeatRepository(context: Context) {
    private lateinit var dbHelper: CarePetDatabase
    init {
        dbHelper = CarePetDatabase(context)
    }
    fun insertHeartBeat(heartBeat: HeartBeat) {
        val db =  dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("pet_ID", heartBeat.petId)
            put("heartbeat_date", heartBeat.heartBeatDate)
            put("heartbeat_time", heartBeat.heartBeatTime)
            put("heartbeat_result", heartBeat.heartBeatResult)
        }
        db.insert("HeartBeats", null, values)
    }
    fun getLastHeartBeat(id: Int): Int {
        val db = dbHelper.readableDatabase
        val cursor: Cursor = db.query("HeartBeats", arrayOf("heartBeat_result"), "pet_ID = ?", arrayOf("$id"), null, null, "heartBeat_ID DESC")
        if (cursor != null) {   // cost?
            if (cursor.moveToNext()) {
                cursor.moveToFirst()
                return cursor.getDouble(cursor.getColumnIndexOrThrow("heartbeat_result")).toInt()
            }
        }
        cursor.close()
        return 0
    }
    fun getAllHeartBeat(id: Int): MutableList<HeartBeat>? {
        val hblist: MutableList<HeartBeat> = mutableListOf()
        val db = dbHelper.readableDatabase
        val cursor: Cursor = db.query("HeartBeats", null, "pet_ID = ?", arrayOf("$id"), null, null, null)
        if (cursor != null) {
            while (cursor.moveToNext()) {
                var heartbeat: HeartBeat = HeartBeat(cursor.getInt(cursor.getColumnIndexOrThrow("heartbeat_ID")),
                    cursor.getInt(cursor.getColumnIndexOrThrow("heartbeat_ID")),
                    cursor.getString(cursor.getColumnIndexOrThrow("heartbeat_date")),
                    cursor.getString(cursor.getColumnIndexOrThrow("heartbeat_time")),
                    cursor.getDouble(cursor.getColumnIndexOrThrow("heartbeat_result")))
                hblist?.add(heartbeat)
            }
            cursor.close()
        }
        return hblist
    }
    fun delHeartBeatById(id: Int) {
        val db = dbHelper.readableDatabase
        db.delete("HeartBeats", "pet_ID LIKE ?", arrayOf("$id"))
    }
}