package com.tfandkusu.learndi

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActionCreator(dispatcher: Dispatcher, private val repository: CardRepository) : ActionCreator(dispatcher) {


    fun load(refresh: Boolean, id: Int) {
        // リフレッシュ開始
        if (refresh)
            dispatcher.dispatch(RefreshRequestAction())
        // 名刺取得
        loadCard(refresh, id)
        // 名刺画像取得
        loadCardImage(refresh, id)
    }

    /**
     * 名刺を取得する
     */
    private fun loadCard(refresh: Boolean, id: Int) = GlobalScope.launch {
        val card = repository.getCard(refresh, id)
        // 名刺取得完了アクション
        dispatcher.dispatch(CardLoadAction(card))

    }

    /**
     * 名刺画像を取得する
     */
    private fun loadCardImage(refresh: Boolean, id: Int) = GlobalScope.launch {
        val bitmap = repository.getCardImage(refresh, id)
        // 名刺画像取得完了アクション
        dispatcher.dispatch(CardImageLoadAction(bitmap))
    }
}