package com.example.carepets_plus.database

import android.app.Application
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.provider.BaseColumns
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class PetRepository(context: Context) {
    private lateinit var dbHelper: CarePetDatabase
    init {
        dbHelper = CarePetDatabase(context)
    }
    fun insertPet(pet: Pet) {
        val db =  dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("pet_name", pet.name)
            put("pet_img", pet.img)
            put("pet_birth", pet.birth)
            put("pet_species", pet.species)
        }
        db.insert("Pets", null, values)
    }
    fun getAllPet(): MutableList<Pet>? {
        val plist: MutableList<Pet> = mutableListOf()
        val db = dbHelper.readableDatabase
        val cursor: Cursor = db.query("Pets", null, null, null, null, null, "pet_ID DESC")
        if (cursor != null) {
            while (cursor.moveToNext()) {
                var pet: Pet = Pet(cursor.getInt(cursor.getColumnIndexOrThrow("pet_ID")),
                cursor.getString(cursor.getColumnIndexOrThrow("pet_name")),
                cursor.getString(cursor.getColumnIndexOrThrow("pet_img")),
                cursor.getString(cursor.getColumnIndexOrThrow("pet_birth")),
                cursor.getString(cursor.getColumnIndexOrThrow("pet_species")))
                plist?.add(pet)
            }
            cursor.close()
        }
        return plist
    }

    fun delPet(id: Int) {
        val db = dbHelper.readableDatabase
        db.delete("Pets", "pet_ID LIKE ?", arrayOf("$id"))
    }
    fun delAllPet() {
        val db = dbHelper.readableDatabase
        db.delete("Pets", null, null)
    }
    fun getPetById(id: Int): Pet? {
        val db = dbHelper.readableDatabase
        val cursor: Cursor = db.query("Pets", null, "pet_ID", arrayOf("$id"), null, null, "pet_ID DESC")
        if (cursor != null) {
            cursor.moveToFirst()
            return Pet(cursor.getInt(cursor.getColumnIndexOrThrow("pet_ID")),
                cursor.getString(cursor.getColumnIndexOrThrow("pet_name")),
                cursor.getString(cursor.getColumnIndexOrThrow("pet_img")),
                cursor.getString(cursor.getColumnIndexOrThrow("pet_birth")),
                cursor.getString(cursor.getColumnIndexOrThrow("pet_species")))
        }
        return null
    }
}