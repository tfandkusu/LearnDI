package com.tfandkusu.learndi

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import kotlinx.coroutines.*
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface CardRemoteDataStore {
    suspend fun getCard(id: Int): Card
    suspend fun getCardImage(url: String): Bitmap
}

class CardRemoteDataStoreImpl : CardRemoteDataStore {


    private val retrofit = Retrofit.Builder()
        .baseUrl("https://jjsonplaceholder.appspot.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val client = OkHttpClient.Builder().build()


    private val service = retrofit.create(CardApiService::class.java)

    override suspend fun getCard(id: Int): Card {
        // わかりやすさのために、わざと待つ
        delay(1000)
        return service.getCard(id)
    }

    override suspend fun getCardImage(url: String): Bitmap {
        val request = Request.Builder().url(url).build()
        val data = withContext(Dispatchers.IO) {
            var data: ByteArray? = null
            try {
                val response = client.newCall(request).execute()
                data = response.body()?.bytes()
            } catch (e: Exception) {
            }
            data
        }
        return withContext(Dispatchers.Default) {
            var bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888)
            data?.let {
                bitmap = BitmapFactory.decodeByteArray(data, 0, data.size)
            }
            bitmap
        }
    }


}