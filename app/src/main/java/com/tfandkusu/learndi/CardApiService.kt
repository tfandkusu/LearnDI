package com.tfandkusu.learndi

import retrofit2.http.GET
import retrofit2.http.Path

interface CardApiService {
    @GET("/cards/{id}")
    suspend fun getCard(@Path("id") id: Int): Card
}