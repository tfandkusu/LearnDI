package com.tfandkusu.learndi

import android.os.Handler
import android.os.Looper
import de.greenrobot.event.EventBus

/**
 * FluxのDispatcher
 */
interface Dispatcher {
    fun dispatch(action: Action)
}

class DispatcherImpl : Dispatcher {

    private val handler = Handler(Looper.getMainLooper())

    override fun dispatch(action: Action) {
        if (isMainThread()) {
            // メインスレッドならばそのまま送る
            EventBus.getDefault().post(action)
        } else {
            // そうでなければメインスレッドで送る
            handler.post { EventBus.getDefault().post(action) }
        }
    }

    /**
     * メインスレッドで動いていればtrueを返却する
     */
    private fun isMainThread(): Boolean {
        return Thread.currentThread().id == Looper.getMainLooper().thread.id
    }
}

/**
 * 実行スレッドを変更しないDispatcher
 */
class DispacherST : Dispatcher {
    override fun dispatch(action: Action) {
        EventBus.getDefault().post(action)
    }
}