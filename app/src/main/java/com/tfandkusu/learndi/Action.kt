package com.tfandkusu.learndi

import android.graphics.Bitmap

open class Action


/**
 * リロードがリクエストされた
 * 引数の無いアクションはシングルトンにする
 */
object RefreshRequestAction: Action()

/**
 * 名刺情報読み込み完了アクション
 */
data class CardLoadAction(val card: Card) : Action()

data class CardImageLoadAction(val bitmap: Bitmap) : Action()