package com.tfandkusu.learndi

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * メイン画面の見た目制御
 * @param repository 使用するRepository。今回の例ではUseCaseを省略する。
 */
class MainPresenter(private val repository: CardRepository) : ViewModel() {
    /**
     * 表示名刺情報
     */
    val card = MutableLiveData<Card>()

    /**
     * プログレス表示フラグ
     */
    val progress = MutableLiveData<Boolean>()

    fun load(refresh: Boolean, id: Int) = GlobalScope.launch {
        // プログレスを表示
        progress.postValue(true)
        // 名刺を取得
        val card = repository.getCard(refresh, id)
        // プログレスを非表示
        progress.postValue(false)
        // 名刺を表示
        this@MainPresenter.card.postValue(card)
    }
}