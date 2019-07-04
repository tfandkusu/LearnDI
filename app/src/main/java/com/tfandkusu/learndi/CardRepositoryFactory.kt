package com.tfandkusu.learndi

class CardRepositoryFactory {
    companion object {
        private val repository = CardRepositoryImpl(CardRemoteDataStoreImpl(), CardLocalDataStoreImpl())

        fun getInstance(): CardRepository {
            return repository
        }

    }
}