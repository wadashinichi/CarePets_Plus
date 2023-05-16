package com.example.carepets_plus.mainpart.search

import okhttp3.ResponseBody
import retrofit2.http.GET

interface ArticleAPI {
    @GET("ApiJson.php")
    suspend fun getArticles(): ResponseBody

//    @GET("ApiJson.php?title=:name")
//    suspend fun getArticlesByName(name: String): ResponseBody

}