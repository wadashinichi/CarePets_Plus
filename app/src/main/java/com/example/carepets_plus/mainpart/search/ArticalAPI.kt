package com.example.carepets_plus.mainpart.search

import okhttp3.ResponseBody
import retrofit2.http.GET

interface ArticalAPI {
    @GET("ApiJson.php")
    suspend fun getArticles(): ResponseBody
}