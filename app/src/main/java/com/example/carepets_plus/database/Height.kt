package com.example.carepets_plus.database

data class Height(
    var heightId: Int? = 0,
    var petId: Int = 0,
    var heightDate: String? = "",
    var heightTime: String? = "",
    var heightResult: Double = 0.0
)
