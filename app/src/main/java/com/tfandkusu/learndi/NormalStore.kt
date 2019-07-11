package com.tfandkusu.learndi

import androidx.lifecycle.ViewModel
import de.greenrobot.event.EventBus

/**
 * FluxのStore。生存時間はActivityのそれと同じ。
 */
open class NormalStore : ViewModel() {
    init {
        // 生成されたときに購読開始
        @Suppress("LeakingThis")
        EventBus.getDefault().register(this)
    }

    override fun onCleared() {
        // ライフサイクルが終わったら購読終了
        EventBus.getDefault().unregister(this)
    }
}