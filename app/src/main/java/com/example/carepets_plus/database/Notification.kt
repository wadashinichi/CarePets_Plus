package com.example.carepets_plus.database

data class Notification(
    var noteId: Int?,
    var petId: Int,
    var name: String,
    var time: String?,
    var state: Int
)
