package com.tfandkusu.learndi

interface CardRepository {
    /**
     * 名刺を取得する
     * @param refresh 必ずリモートから取得するフラグ
     * @param id 名刺ID
     */
    suspend fun getCard(refresh: Boolean,id: Int): Card
}

class CardRepositoryImpl(
    private val remote: CardRemoteDataStore,
    private val local: CardLocalDataStore
) : CardRepository {
    @Suppress("LiftReturnOrAssignment")
    override suspend fun getCard(refresh: Boolean, id: Int): Card {
        val localCard = local.getCard(id)
        val currentTime = System.currentTimeMillis()
        if (!refresh && localCard.value != null && currentTime - localCard.time < 3 * 1000) {
            // 3秒以内のキャッシュがあればそれを返却する
            return localCard.value
        } else {
            // 無ければAPIから取得する
            val card = remote.getCard(id)
            // キャッシュする
            local.saveCard(card)
            // 返却する
            return card
        }
    }
}