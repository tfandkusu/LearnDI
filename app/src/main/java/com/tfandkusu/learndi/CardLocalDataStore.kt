package com.tfandkusu.learndi

interface CardLocalDataStore {
    fun getCard(id: Int): LocalData<Card>

    fun saveCard(card: Card)

    fun getCardImage(url: String): LocalData<CardImage>

    fun saveCardImage(cardImage: CardImage)


}

class CardLocalDataStoreImpl : CardLocalDataStore {

    private var card = LocalData<Card>(null, 0)

    private var cardImage = LocalData<CardImage>(null, 0)

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

    override fun getCardImage(url: String): LocalData<CardImage> {
        // 同じURLならば返却する
        if (cardImage.value?.url == url)
            return cardImage
        else
            return LocalData(null, 0)
    }

    override fun saveCardImage(cardImage: CardImage) {
        this.cardImage = LocalData(cardImage, System.currentTimeMillis())
    }

}