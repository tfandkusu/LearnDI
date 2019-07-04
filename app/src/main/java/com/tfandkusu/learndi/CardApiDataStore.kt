package com.tfandkusu.learndi

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface CardApiDataStore {
    suspend fun getCard(id: Int): Card
}

class CardApiDataStoreImpl : CardApiDataStore {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://jjsonplaceholder.appspot.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(CardApiService::class.java)

    override suspend fun getCard(id: Int): Card {
        return service.getCard(id)
    }

}