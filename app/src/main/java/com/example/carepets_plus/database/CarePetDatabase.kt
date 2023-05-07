package com.example.carepets_plus.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class CarePetDatabase(context: Context) : SQLiteOpenHelper(context, "CarePetDatabase", null, 1) {
    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL(createTableQuery("Pets", "pet_ID",
            "pet_name TEXT NOT NULL , " +
                    "pet_img TEXT, " +
                    "pet_birth TEXT NOT NULL, " +
                    "pet_species TEXT NOT NULL"))
        p0?.execSQL(createTableQuery("Weights", "weight_ID",
            "pet_ID INTEGER NOT NULL, " +
                    "weight_date TEXT, " +
                    "weight_time TEXT, " +
                    "weight_result TEXT NOT NULL"))
        p0?.execSQL(createTableQuery("Heights", "Height_ID",
            "pet_ID INTEGER NOT NULL, " +
                    "height_date TEXT, " +
                    "height_time TEXT, " +
                    "height_result TEXT NOT NULL"))
        p0?.execSQL(createTableQuery("HeartBeats", "heartbeat_ID",
            "pet_ID INTEGER NOT NULL, " +
                    "heartbeat_date TEXT, " +
                    "heartbeat_time TEXT, " +
                    "heartbeat_result TEXT NOT NULL"))
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }

    private fun createTableQuery(tableName: String, primaryKey: String, column: String): String
    = "CREATE TABLE $tableName (" +
            "$primaryKey INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "$column)"
}