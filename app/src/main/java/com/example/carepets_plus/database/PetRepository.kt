package com.example.carepets_plus.database

import android.app.Application
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class PetRepository(var application: Application) {

    private val db = Firebase.firestore

    fun insert(pet: Pet) = db.collection("pets")
        .add(pet)
        .addOnSuccessListener {
            Toast.makeText(application.applicationContext, "Just added a pet", Toast.LENGTH_SHORT)
        }
        .addOnFailureListener {
            Toast.makeText(application.applicationContext, "Adding pet is failed", Toast.LENGTH_SHORT)
        }

    fun getAll(): List<Pet> = db.collection("pets").get() as List<Pet>
}