package com.example.carepets_plus.database

data class HeartBeat(
    var heartBeatId: Int? = 0,
    var petId: Int = 1,
    var heartBeatDate: String? = "",
    var heartBeatTime: String? = "",
    var heartBeatResult: Double = 0.0)