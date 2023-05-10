package com.example.carepets_plus.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor

class HeightRepository(context: Context) {
    private lateinit var dbHelper: CarePetDatabase
    init {
        dbHelper = CarePetDatabase(context)
    }
    fun insertHeight(height: Height) {
        val db =  dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("pet_ID", height.petId)
            put("height_date", height.heightDate)
            put("height_time", height.heightTime)
            put("height_result", height.heightResult)
        }
        db.insert("Heights", null, values)
    }
    fun getLastHeight(id: Int): Double {
        val db = dbHelper.readableDatabase
        val cursor: Cursor = db.query("Heights", arrayOf("height_result"), "pet_ID = ?", arrayOf("$id"), null, null, "height_ID DESC")
        if (cursor != null) {   // cost?
            if (cursor.moveToNext()) {
                cursor.moveToFirst()
                return cursor.getDouble(cursor.getColumnIndexOrThrow("height_result"))
            }
        }
        cursor.close()
        return 0.0
    }
}