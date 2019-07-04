package com.tfandkusu.learndi

import kotlinx.coroutines.delay
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface CardRemoteDataStore {
    suspend fun getCard(id: Int): Card
}

class CardRemoteDataStoreImpl : CardRemoteDataStore {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://jjsonplaceholder.appspot.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(CardApiService::class.java)

    override suspend fun getCard(id: Int): Card {
        // わかりやすさのために、わざと待つ
        delay(1000)
        return service.getCard(id)
    }

}