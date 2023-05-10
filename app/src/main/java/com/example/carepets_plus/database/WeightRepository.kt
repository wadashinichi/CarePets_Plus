package com.example.carepets_plus.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor

class WeightRepository(context: Context) {
    private lateinit var dbHelper: CarePetDatabase
    init {
        dbHelper = CarePetDatabase(context)
    }
    fun insertWeight(weight: Weight) {
        val db =  dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("pet_ID", weight.petId)
            put("weight_date", weight.weightDate)
            put("weight_time", weight.weightTime)
            put("weight_result", weight.weightResult)
        }
        db.insert("Weights", null, values)
    }
    fun getLastWeight(id: Int): Double {
        val db = dbHelper.readableDatabase
        val cursor: Cursor = db.query("Weights", arrayOf("weight_result"), "pet_ID = ?", arrayOf("$id"), null, null, "weight_ID DESC")
        if (cursor != null) {
            if (cursor.moveToNext()) {
                cursor.moveToFirst()
                return cursor.getDouble(cursor.getColumnIndexOrThrow("weight_result"))
            }
        }
        cursor.close()
        return 0.0
    }
}