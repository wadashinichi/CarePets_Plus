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
            put("height_date", heartBeat.heartBeatDate)
            put("height_time", heartBeat.heartBeatTime)
            put("height_result", heartBeat.heartBeatResult)
        }
        db.insert("Heights", null, values)
    }
    fun getLastHeartBeat(id: Int): Int {
        val db = dbHelper.readableDatabase
        val cursor: Cursor = db.query("HeartBeats", arrayOf("heartBeat_result"), "pet_ID = ?", arrayOf("$id"), null, null, "heartBeat_ID DESC")
        if (cursor != null) {   // cost?
            if (cursor.moveToNext()) {
                cursor.moveToFirst()
                return cursor.getDouble(cursor.getColumnIndexOrThrow("heartBeat_result")).toInt()
            }
        }
        cursor.close()
        return 0
    }
}