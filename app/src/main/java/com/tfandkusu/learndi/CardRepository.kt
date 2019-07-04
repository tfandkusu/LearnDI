package com.tfandkusu.learndi

interface CardRepository {
    /**
     * 名刺を取得する
     * @param refresh 必ずリモートから取得するフラグ
     * @param id 名刺ID
     */
    suspend fun getCard(refresh: Boolean,id: Int): Card
}

/**
 * 名刺のRepository
 * @param remote APIから出し入れするDataStore
 * @param local ローカルから出し入れするDataStore
 */
class CardRepositoryImpl(
    private val remote: CardRemoteDataStore,
    private val local: CardLocalDataStore
) : CardRepository {
    @Suppress("LiftReturnOrAssignment")
    override suspend fun getCard(refresh: Boolean, id: Int): Card {
        // まずローカルから取得する
        val localCard = local.getCard(id)
        // 現在時刻を取得する
        val currentTime = System.currentTimeMillis()
        //
        if (!refresh && localCard.value != null && currentTime - localCard.time < 3 * 1000) {
            // リフレッシュフラグオフかつローカルのデータがありローカルの保存時刻から3秒たっていない
            return localCard.value
        } else {
            // そうで無ければAPIから取得する
            val card = remote.getCard(id)
            // キャッシュする
            local.saveCard(card)
            // 返却する
            return card
        }
    }
}