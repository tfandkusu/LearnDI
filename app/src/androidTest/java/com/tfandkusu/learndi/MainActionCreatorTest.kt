package com.tfandkusu.learndi

import android.graphics.Bitmap
import io.mockk.*
import org.junit.Test


class MainActionCreatorTest {
    /**
     * 起動のテストを行う
     */
    @Test
    fun testLoad() {
        // Dispatcherのモック(なにもしない)
        val dispatcher = mockk<Dispatcher>(relaxed = true)
        // Repositoryのモック
        val repository = mockk<CardRepository>()
        val card = Card(2, "佐藤 麻衣", "高橋化学株式会社", "企画部", "主任")
        val bitmap = Bitmap.createBitmap(10, 11, Bitmap.Config.ARGB_8888)
        // モックが返却する値を定義する
        // suspendなメソッドにはcoEveryを使う
        coEvery { repository.getCard(false, 2) } returns card
        coEvery { repository.getCardImage(false, 2) } returns bitmap
        // actionCreatorを作成
        val actionCreator = MainActionCreator(dispatcher, repository)
        // ロードメソッドを読み込む
        actionCreator.load(false, 2)
        // CardLoadActionとCardImageLoadActionが発行されることを確認
        verifyAll {
            dispatcher.dispatch(CardLoadAction(card))
            dispatcher.dispatch(CardImageLoadAction(bitmap))
        }
        // RefreshRequestActionは呼ばれないことを確認
        verify(exactly = 0) {
            dispatcher.dispatch(RefreshRequestAction)
        }
    }

    /**
     * 下スワイプ読み込みのテストを行う
     */
    @Test
    fun testRefresh() {
        // Dispatcherのモック(なにもしない)
        val dispatcher = mockk<Dispatcher>(relaxed = true)
        // Repositoryのモック
        val repository = mockk<CardRepository>()
        val card = Card(2, "佐藤 麻衣", "高橋化学株式会社", "企画部", "主任")
        val bitmap = Bitmap.createBitmap(10, 11, Bitmap.Config.ARGB_8888)
        // モックが返却する値を定義する
        // suspendなメソッドにはcoEveryを使う
        coEvery { repository.getCard(true, 2) } returns card
        coEvery { repository.getCardImage(true, 2) } returns bitmap
        // actionCreatorを作成
        val actionCreator = MainActionCreator(dispatcher, repository)
        // ロードメソッドを読み込む
        actionCreator.load(true, 2)
        // CardLoadActionとCardImageLoadActionが発行されることを確認
        verifyAll {
            dispatcher.dispatch(RefreshRequestAction)
            dispatcher.dispatch(CardLoadAction(card))
            dispatcher.dispatch(CardImageLoadAction(bitmap))
            // 順番をテストできなかった。
            // 2番目と3番目は順不同
        }
    }
}