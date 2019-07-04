package com.tfandkusu.learndi

interface CardRepository {
    suspend fun getCard(id: Int): Card
}

class CardRepositoryImpl(private val apiStore: CardApiDataStore) : CardRepository {
    override suspend fun getCard(id: Int): Card {
        return apiStore.getCard(id)
    }
}