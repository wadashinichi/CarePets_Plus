package com.example.carepets_plus.database

data class Weight(
    var weightId: Int? = 0,
    var petId: Int = 1,
    var weightDate: String? = "",
    var weightTime: String? = "",
    var weightResult: Double = 0.0)