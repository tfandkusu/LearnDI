package com.tfandkusu.learndi

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActionCreator(dispatcher: Dispatcher, private val repository: CardRepository) : ActionCreator(dispatcher) {


    fun load(refresh: Boolean, id: Int) = GlobalScope.launch {
        // リフレッシュ開始
        if (refresh)
            dispatcher.dispatch(RefreshRequestAction())
        // 名刺を取得
        val card = repository.getCard(refresh, id)
        // 名刺取得完了アクション
        dispatcher.dispatch(CardLoadAction(card))
    }
}