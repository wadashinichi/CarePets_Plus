package com.example.carepets_plus.database


//@IgnoreExtraProperties
data class Pet(
    var id: Int? = 0,
    var name: String? = "",
    var img: String? = "",
    var birth: String? = "",
    var species: String? = ""
)