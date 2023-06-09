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
    fun getAllWeight(id: Int): MutableList<Weight>? {
        val wlist: MutableList<Weight> = mutableListOf()
        val db = dbHelper.readableDatabase
        val cursor: Cursor = db.query("Weights", null, "pet_ID = ?", arrayOf("$id"), null, null, null)
        if (cursor.count > 0) {
            while (cursor.moveToNext()) {
                var weight: Weight = Weight(cursor.getInt(cursor.getColumnIndexOrThrow("weight_ID")),
                    cursor.getInt(cursor.getColumnIndexOrThrow("pet_ID")),
                    cursor.getString(cursor.getColumnIndexOrThrow("weight_date")),
                    cursor.getString(cursor.getColumnIndexOrThrow("weight_time")),
                    cursor.getDouble(cursor.getColumnIndexOrThrow("weight_result")))
                wlist?.add(weight)
            }
            cursor.close()
        }
        return wlist
    }
    fun delWeightById(id: Int) {
        val db = dbHelper.readableDatabase
        db.delete("Weights", "pet_ID LIKE ?", arrayOf("$id"))
    }
}