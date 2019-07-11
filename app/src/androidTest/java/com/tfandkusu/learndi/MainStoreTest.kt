package com.tfandkusu.learndi

import android.graphics.Bitmap
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.kotlintest.shouldBe
import org.junit.Rule
import org.junit.Test

class MainStoreTest {
    @Rule
    @JvmField
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val dispatcher = DispacherST()

    /**
     * 名刺が名刺画像よりも先に読み込まれた
     */
    @Test
    fun testLoadCardFirst() {
        val store = MainStore()
        val card = Card(2, "佐藤 麻衣", "高橋化学株式会社", "企画部", "主任")
        val bitmap = Bitmap.createBitmap(10, 11, Bitmap.Config.ARGB_8888)
        // 最初の状態
        // Kotlinは中置記法が使える
        store.progress.value shouldBe true
        store.refresh.value shouldBe false
        store.refreshEnabled.value shouldBe false
        store.card.value shouldBe null
        store.bitmap.value shouldBe null
        // 名刺が読み込まれた
        dispatcher.dispatch(CardLoadAction(card))
        // 画像がないのでまだ更新しない
        store.progress.value shouldBe true
        store.refresh.value shouldBe false
        store.refreshEnabled.value shouldBe false
        store.card.value shouldBe null
        store.bitmap.value shouldBe null
        // 画像が名刺が読み込まれた
        dispatcher.dispatch(CardImageLoadAction(bitmap))
        // 名刺と画像を表示する
        store.progress.value shouldBe false
        store.refresh.value shouldBe false
        store.refreshEnabled.value shouldBe true
        store.card.value shouldBe card
        store.bitmap.value shouldBe bitmap
        store.clear()
    }

    /**
     * 名刺画像が名刺よりも先に読み込まれた
     */
    @Test
    fun testLoadCardImageFirst() {
        val store = MainStore()
        val card = Card(2, "佐藤 麻衣", "高橋化学株式会社", "企画部", "主任")
        val bitmap = Bitmap.createBitmap(10, 11, Bitmap.Config.ARGB_8888)
        // 最初の状態
        store.progress.value shouldBe true
        store.refresh.value shouldBe false
        store.refreshEnabled.value shouldBe false
        store.card.value shouldBe null
        store.bitmap.value shouldBe null
        dispatcher.dispatch(CardImageLoadAction(bitmap))
        store.progress.value shouldBe true
        store.refresh.value shouldBe false
        store.refreshEnabled.value shouldBe false
        store.card.value shouldBe null
        store.bitmap.value shouldBe null
        dispatcher.dispatch(CardLoadAction(card))
        store.progress.value shouldBe false
        store.refresh.value shouldBe false
        store.refreshEnabled.value shouldBe true
        store.card.value shouldBe card
        store.bitmap.value shouldBe bitmap
        store.clear()
    }
}