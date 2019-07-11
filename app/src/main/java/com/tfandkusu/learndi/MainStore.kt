package com.tfandkusu.learndi

import androidx.lifecycle.MutableLiveData

class MainStore : NormalStore() {
    /**
     * 表示名刺情報
     */
    val card = MutableLiveData<Card>()

    /**
     * プログレス表示フラグ
     */
    val progress = MutableLiveData<Boolean>()

    /**
     * リフレッシュ中フラグ
     */
    val refresh = MutableLiveData<Boolean>()

    /**
     * リフレッシュできるフラグ
     */
    val refreshEnabled = MutableLiveData<Boolean>()

    init {
        progress.value = true
        refresh.value = false
        refreshEnabled.value = false
    }

    /**
     * リフレッシュ操作がリクエストされた
     */
    fun onEvent(action: RefreshRequestAction){
        refresh.value = true
    }

    /**
     * 名刺の読み込みが完了した
     */
    fun onEvent(action: CardLoadAction){
        card.value = action.card
        progress.value = false
        refresh.value = false
        // リフレッシュ操作が再び有効に
        refreshEnabled.value = true
    }
}