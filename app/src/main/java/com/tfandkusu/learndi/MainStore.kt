package com.tfandkusu.learndi

import android.graphics.Bitmap
import androidx.lifecycle.MutableLiveData

class MainStore : NormalStore() {
    /**
     * 表示名刺情報
     */
    val card = MutableLiveData<Card>()

    /**
     * 表示名刺画像
     */
    val bitmap = MutableLiveData<Bitmap>()

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
    fun onEvent(action: RefreshRequestAction) {
        refresh.value = true
    }

    private var cardData: Card? = null

    private var bitmapData: Bitmap? = null


    /**
     * 名刺の読み込みが完了した
     */
    fun onEvent(action: CardLoadAction) {
        cardData = action.card
        update()
    }

    /**
     * 名刺画像の読み込みが完了した
     */
    fun onEvent(action: CardImageLoadAction) {
        bitmapData = action.bitmap
        update()
    }

    /**
     * 表示を更新する
     */
    private fun update() {
        if (cardData != null && bitmapData != null) {
            card.value = cardData
            bitmap.value = bitmapData
            progress.value = false
            refresh.value = false
            // リフレッシュ操作が再び有効に
            refreshEnabled.value = true
        }
    }
}