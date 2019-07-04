package com.tfandkusu.learndi

interface CardLocalDataStore {
    fun getCard(id: Int): LocalData<Card>
    fun saveCard(card: Card)
}

class CardLocalDataStoreImpl : CardLocalDataStore {

    private var card = LocalData<Card>(null, 0)

    override fun getCard(id: Int): LocalData<Card> {
        // 同じIDならば返却する
        if (card.value?.id == id)
            return card
        else
            return LocalData(null, 0)
    }

    override fun saveCard(card: Card) {
        this.card = LocalData(card, System.currentTimeMillis())
    }
}